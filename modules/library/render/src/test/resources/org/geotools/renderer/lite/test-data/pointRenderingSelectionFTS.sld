<?xml version="1.0" encoding="UTF-8"?>
<StyledLayerDescriptor xmlns="http://www.opengis.net/sld" xmlns:ogc="http://www.opengis.net/ogc" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="1.0.0" xsi:schemaLocation="http://www.opengis.net/sld http://schemas.opengis.net/sld/1.0.0/StyledLayerDescriptor.xsd">
   <NamedLayer>
      <Name>Grass</Name>
      <UserStyle>
         <FeatureTypeStyle>
            <Rule>
               <ogc:Filter>
                  <ogc:PropertyIsLessThan>
                     <ogc:PropertyName>rotation</ogc:PropertyName>
                     <ogc:Literal>90</ogc:Literal>
                  </ogc:PropertyIsLessThan>
               </ogc:Filter>
               <PointSymbolizer>
                  <Graphic>
                     <Mark>
                        <WellKnownName>circle</WellKnownName>
                        <Fill>
                           <CssParameter name="fill">0xFF0000</CssParameter>
                        </Fill>
                     </Mark>
                     <Size>32</Size>
                  </Graphic>
               </PointSymbolizer>
            </Rule>
         </FeatureTypeStyle>
         <FeatureTypeStyle>
            <Rule>
               <ogc:Filter>
               <ogc:And>
                  <ogc:PropertyIsGreaterThanOrEqualTo>
                     <ogc:PropertyName>rotation</ogc:PropertyName>
                     <ogc:Literal>90</ogc:Literal>
                  </ogc:PropertyIsGreaterThanOrEqualTo>
                  <ogc:PropertyIsLessThan>
                     <ogc:PropertyName>rotation</ogc:PropertyName>
                     <ogc:Literal>180</ogc:Literal>
                  </ogc:PropertyIsLessThan>
                  </ogc:And>
               </ogc:Filter>
               <PointSymbolizer>
                  <Graphic>
                     <Mark>
                        <WellKnownName>circle</WellKnownName>
                        <Fill>
                           <CssParameter name="fill">#6495ED</CssParameter>
                        </Fill>
                     </Mark>
                     <Size>32</Size>
                  </Graphic>
               </PointSymbolizer>
            </Rule>
            <Rule>
               <ogc:Filter>
                <ogc:And>
                  <ogc:PropertyIsGreaterThanOrEqualTo>
                     <ogc:PropertyName>rotation</ogc:PropertyName>
                     <ogc:Literal>180</ogc:Literal>
                  </ogc:PropertyIsGreaterThanOrEqualTo>
                  <ogc:PropertyIsLessThan>
                     <ogc:PropertyName>rotation</ogc:PropertyName>
                     <ogc:Literal>360</ogc:Literal>
                  </ogc:PropertyIsLessThan>
                  </ogc:And>
               </ogc:Filter>
               <PointSymbolizer>
                  <Graphic>
                     <Mark>
                        <WellKnownName>circle</WellKnownName>
                        <Fill>
                           <CssParameter name="fill">#CCCCFF</CssParameter>
                        </Fill>
                     </Mark>
                     <Size>32</Size>
                  </Graphic>
               </PointSymbolizer>
               <VendorOption name="inclusion">legendOnly</VendorOption>
            </Rule>
            <Rule>
               <ogc:Filter>
                  <ogc:PropertyIsEqualTo>
                     <ogc:PropertyName>rotation</ogc:PropertyName>
                     <ogc:Literal>360</ogc:Literal>
                  </ogc:PropertyIsEqualTo>
               </ogc:Filter>
               <PointSymbolizer>
                  <Graphic>
                     <Mark>
                        <WellKnownName>circle</WellKnownName>
                        <Fill>
                           <CssParameter name="fill">#DE3163</CssParameter>
                        </Fill>
                     </Mark>
                     <Size>32</Size>
                  </Graphic>
               </PointSymbolizer>
               <VendorOption name="inclusion">legendOnly</VendorOption>
            </Rule>
           <VendorOption name="inclusion">legendOnly</VendorOption>
         </FeatureTypeStyle>
      </UserStyle>
   </NamedLayer>
</StyledLayerDescriptor>