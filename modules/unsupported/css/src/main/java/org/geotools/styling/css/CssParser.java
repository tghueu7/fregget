/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2014, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.styling.css;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.geotools.filter.text.cql2.CQLException;
import org.geotools.filter.text.ecql.ECQL;
import org.geotools.styling.css.selector.Data;
import org.geotools.styling.css.selector.Id;
import org.geotools.styling.css.selector.Or;
import org.geotools.styling.css.selector.PseudoClass;
import org.geotools.styling.css.selector.ScaleRange;
import org.geotools.styling.css.selector.Selector;
import org.geotools.styling.css.selector.TypeName;
import org.opengis.filter.Filter;
import org.parboiled.Action;
import org.parboiled.BaseParser;
import org.parboiled.Context;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.annotations.SuppressNode;
import org.parboiled.annotations.SuppressSubnodes;
import org.parboiled.support.ValueStack;

/**
 * Parser for the cartographic CSS
 * 
 * @author Andrea Aime - GeoSolutions
 */
@BuildParseTree
public class CssParser extends BaseParser<Object> {

    static final Object MARKER = new Object();

    Rule StyleSheet() {
        return Sequence(OneOrMore(CssRule(), WhiteSpaceOrComment()), EOI, push(new Stylesheet(
                popAll(CssRule.class))));
    }

    Rule CssRule() {
        return Sequence(Optional(RuleComment()), WhiteSpace(), Selector(), WhiteSpace(),//
                '{', WhiteSpaceOrIgnoredComment(), //
                PropertyList(), WhiteSpaceOrIgnoredComment(), '}', new Action() {

                    @Override
                    public boolean run(Context ctx) {
                        List properties = (List) pop();
                        Selector selector = (Selector) pop();
                        if (!ctx.getValueStack().isEmpty() && peek() instanceof String) {
                            String comment = (String) pop();
                            comment = comment.trim();
                            // get rid of the extra comments between rules
                            while (!ctx.getValueStack().isEmpty() && peek() instanceof String) {
                                pop();
                            }
                            push(new CssRule(comment, selector, properties));
                        } else {
                            push(new CssRule(selector, properties));
                        }

                        return true;
                    }

                });
    }

    Rule Selector() {
        return FirstOf(OrSelector(), AndSelector(), BasicSelector());
    }

    Rule BasicSelector() {
        return FirstOf(CatchAllSelector(), ECQLSelector(), MinScaleSelector(), MaxScaleSelector(),
                IdSelector(), PseudoClassSelector(), NumberedPseudoClassSelector(),
                TypenameSelector());
    }

    Rule AndSelector() {
        return Sequence(BasicSelector(), WhiteSpace(), FirstOf(AndSelector(), BasicSelector()), //
                swap() && push(Selector.and((Selector) pop(), (Selector) pop())));
    }

    Rule OrSelector() {
        return Sequence(FirstOf(AndSelector(), BasicSelector()), WhiteSpace(), ',', WhiteSpace(),
                Selector(), //
                swap() && push(new Or((Selector) pop(), (Selector) pop())));
    }

    @SuppressSubnodes
    Rule PseudoClassSelector() {
        return Sequence(':', ClassName(), push(PseudoClass.newPseudoClass((String) pop())));
    }

    @SuppressSubnodes
    Rule NumberedPseudoClassSelector() {
        return Sequence(":nth-", ClassName(), '(', Number(), push(match()), ')', swap()
                && push(PseudoClass.newPseudoClass((String) pop(), Integer.valueOf((String) pop()))));
    }

    Rule ClassName() {
        return Sequence(FirstOf("mark", "stroke", "fill", "symbol", "shield"), push(match()));
    }

    @SuppressSubnodes
    Rule TypenameSelector() {
        return Sequence(Sequence(Identifier(), Optional(':', Identifier())),
                push(new TypeName(match())));
    }

    @SuppressSubnodes
    Rule IdSelector() {
        return Sequence(
                '#',
                Sequence(Identifier(), Optional(':', Identifier()),
                        Optional('.', Sequence(TestNot(AnyOf("\"'[]")), ANY))),
                push(new Id(match())));
    }

    Rule CatchAllSelector() {
        return Sequence('*', push(Selector.ACCEPT));
    }

    Rule MaxScaleSelector() {
        return Sequence("[", WhiteSpace(), "@scale", WhiteSpace(), "<", WhiteSpace(), Number(),
                push(new ScaleRange(0, true, Double.valueOf(match()), false)), //
                WhiteSpace(), "]");
    }

    Rule MinScaleSelector() {
        return Sequence("[", WhiteSpace(), "@scale", WhiteSpace(), ">", WhiteSpace(), Number(),
                push(new ScaleRange(Double.valueOf(match()), true, Double.POSITIVE_INFINITY, true)), //
                WhiteSpace(), "]");
    }

    Rule PropertyList() {
        return Sequence(
                OneOrMore(Sequence(Property(), WhiteSpace(), ';', WhiteSpaceOrIgnoredComment())),
                push(popAll(Property.class)));
    }

    Rule Property() {
        return Sequence(Identifier(), push(match()), WhiteSpace(), Colon(), WhiteSpace(), //
                Sequence(Value(), WhiteSpace(), ZeroOrMore(',', WhiteSpace(), Value())), //
                push(popAll(Value.class)) && swap()
                        && push(new Property(pop(String.class), pop(List.class))));
    }

    @SuppressNode
    Rule Colon() {
        return Ch(':');
    }

    Rule Value() {
        return FirstOf(MultiValue(), SimpleValue());
    }

    Rule SimpleValue() {
        return FirstOf(URLFunction(), Function(), Color(), NamedColor(), Measure(), String(),
                ValueIdentifier(), ECQLExpression());
    }

    Rule MultiValue() {
        return Sequence(push(MARKER), SimpleValue(), OneOrMore(WhiteSpace1(), SimpleValue()),
                push(new Value.Multi(popAll(Value.class))));
    }

    Rule Function() {
        return Sequence(Identifier(), push(match()), '(', Value(),
                ZeroOrMore(WhiteSpace(), ',', WhiteSpace(), Value()), ')', push(new Value.Function(
                        popAll(Value.class), (String) pop())));
    }

    Rule URLFunction() {
        return Sequence("url", WhiteSpace(), "(", WhiteSpace(), URL(), WhiteSpace(), ")",
                push(new Value.Function("url", (Value) pop())));
    }

    /**
     * Very relaxed URL matcher, as we need to match also relative urls
     * 
     * @return
     */
    Rule URL() {
        return Sequence(OneOrMore(FirstOf(Alphanumeric(), AnyOf("-._]:/?#[]@|$&'*+,;="))),
                push(new Value.Literal(match())));
    }

    Rule ValueIdentifier() {
        return Sequence(Identifier(), push(new Value.Literal(match())));
    }

    Rule String() {
        return FirstOf(
                Sequence('\'', ZeroOrMore(Sequence(TestNot(AnyOf("\r\n'\\")), ANY)),
                        push(new Value.Literal(match())), '\''),
                Sequence('"', ZeroOrMore(Sequence(TestNot(AnyOf("\r\n\"\\")), ANY)),
                        push(new Value.Literal(match())), '"'));
    }

    Rule Measure() {
        return Sequence(
                Sequence(
                        Number(),
                        Optional(FirstOf(String("px"), String("m"), String("ft"), String("%"),
                                String("deg")))), push(new Value.Literal(match())));
    }

    Rule ECQLExpression() {
        return ECQL(new Action() {
            @Override
            public boolean run(Context ctx) {
                String expression = match();
                try {
                    org.opengis.filter.expression.Expression e = ECQL.toExpression(expression);
                    ctx.getValueStack().push(new Value.Expression(e));
                    return true;
                } catch (CQLException e) {
                    return false;
                }
            }
        });
    }

    Rule ECQLSelector() {
        return ECQL(new Action() {
            @Override
            public boolean run(Context ctx) {
                String expression = match();
                try {
                    Filter f = ECQL.toFilter(expression);
                    ctx.getValueStack().push(new Data(f));
                    return true;
                } catch (CQLException e) {
                    return false;
                }
            }
        });
    }

    Rule ECQL(Action parserChecker) {
        return Sequence(
                '[',
                OneOrMore(FirstOf(SingleQuotedString(), DoubleQuotedString(),
                        Sequence(TestNot(AnyOf("\"'[]")), ANY))), //
                parserChecker, ']');
    }

    Rule DoubleQuotedString() {
        return Sequence('"', ZeroOrMore(Sequence(TestNot(AnyOf("\r\n\"\\")), ANY)), '"');
    }

    Rule SingleQuotedString() {
        return Sequence('\'', ZeroOrMore(Sequence(TestNot(AnyOf("\r\n'\\")), ANY)), '\'');
    }

    Rule IntegralNumber() {
        return OneOrMore(Digit());
    }

    Rule Number() {
        return Sequence(Optional(AnyOf("-+")), OneOrMore(Digit()),
                Optional('.', ZeroOrMore(Digit())));
    }

    @SuppressSubnodes
    Rule Color() {
        return Sequence(
                Sequence(
                        '#',
                        FirstOf(Sequence(Hex(), Hex(), Hex(), Hex(), Hex(), Hex()),
                                Sequence(Hex(), Hex(), Hex()))), push(new Value.Literal(
                        toHexColor(match()))));
    }

    String toHexColor(String hex) {
        if (hex.length() == 7) {
            return hex;
        } else {
            char r = hex.charAt(1);
            char g = hex.charAt(2);
            char b = hex.charAt(3);
            return "#" + r + r + g + g + b + b;
        }
    }

    @SuppressSubnodes
    Rule NamedColor() {
        String[] colorNames = new String[Value.COLORS_TO_HEX.size()];
        int i = 0;
        for (String name : Value.COLORS_TO_HEX.keySet()) {
            colorNames[i++] = name;
        }
        // make sure the longer words come before the shorter ones (yellowgreen before yellow)
        Arrays.sort(colorNames, Collections.reverseOrder());

        Rule[] insensitiveColorNames = new Rule[colorNames.length];
        for (int j = 0; j < colorNames.length; j++) {
            insensitiveColorNames[j] = IgnoreCase(colorNames[j]);
        }

        return Sequence(FirstOf(insensitiveColorNames), new Action() {

            @Override
            public boolean run(Context ctx) {
                String hex = Value.COLORS_TO_HEX.get(match().toLowerCase());
                push(new Value.Literal(hex));
                return true;
            }

        });
    }

    @SuppressNode
    Rule Identifier() {
        return Sequence(Optional('-'), NameStart(), ZeroOrMore(NameCharacter()));
    }

    @SuppressNode
    Rule NameStart() {
        return FirstOf('_', Alpha());
    }

    @SuppressNode
    Rule NameCharacter() {
        return FirstOf(AnyOf("-_"), Alphanumeric());
    }

    @SuppressNode
    Rule Hex() {
        return FirstOf(CharRange('a', 'f'), CharRange('A', 'F'), Digit());
    }

    @SuppressNode
    Rule Digit() {
        return CharRange('0', '9');
    }

    @SuppressNode
    Rule Alphanumeric() {
        return FirstOf(Alpha(), Digit());
    }

    @SuppressNode
    Rule Alpha() {
        return FirstOf(CharRange('a', 'z'), CharRange('A', 'Z'));
    }

    Rule IgnoredComment() {
        return Sequence("/*", OneOrMore(TestNot("*/"), ANY), "*/");
    }

    Rule RuleComment() {
        return Sequence("/*", OneOrMore(TestNot("*/"), ANY), push(match()), "*/");
    }

    @SuppressNode
    Rule WhiteSpaceOrIgnoredComment() {
        return Optional(OneOrMore(FirstOf(IgnoredComment(), WhiteSpace1())));
    }

    @SuppressNode
    Rule WhiteSpaceOrComment() {
        return Optional(OneOrMore(FirstOf(RuleComment(), WhiteSpace1())));
    }


    @SuppressNode
    Rule WhiteSpace() {
        return ZeroOrMore(AnyOf(" \r\t\f\n"));
    }

    @SuppressNode
    Rule WhiteSpace1() {
        return OneOrMore(AnyOf(" \r\t\f\n"));
    }

    /**
     * We redefine the rule creation for string literals to automatically match trailing whitespace
     * if the string literal ends with a space character, this way we don't have to insert extra
     * whitespace() rules after each character or string literal
     */
    @Override
    protected Rule fromStringLiteral(String string) {
        return string.matches("\\s+$") ? Sequence(String(string.substring(0, string.length() - 1)),
                WhiteSpace()) : String(string);
    }

    <T> T pop(Class<T> clazz) {
        return (T) pop();
    }

    <T> List<T> popAll(Class<T> clazz) {
        ValueStack<Object> valueStack = getContext().getValueStack();
        List<T> result = new ArrayList<T>();
        while (!valueStack.isEmpty() && clazz.isInstance(valueStack.peek())) {
            result.add((T) valueStack.pop());
        }
        if (!valueStack.isEmpty() && valueStack.peek() == MARKER) {
            valueStack.pop();
        }
        Collections.reverse(result);

        return result;
    }
}