/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2004-2008, Open Source Geospatial Foundation (OSGeo)
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
 *
 *    This package contains documentation from OpenGIS specifications.
 *    OpenGIS consortium's work is fully acknowledged here.
 */
package org.geotools.metadata.iso.identification;

import java.util.Collection;
import org.geotools.metadata.iso.MetadataEntity;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.distribution.Format;
import org.opengis.metadata.identification.AggregateInformation;
import org.opengis.metadata.identification.BrowseGraphic;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.identification.Keywords;
import org.opengis.metadata.identification.Progress;
import org.opengis.metadata.identification.Usage;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.util.InternationalString;

/**
 * Basic information required to uniquely identify a resource or resources.
 *
 * @version $Id$
 * @author Martin Desruisseaux (IRD)
 * @author Touraïvane
 * @since 2.1
 */
public class IdentificationImpl extends MetadataEntity implements Identification {
    /** Serial number for compatibility with different versions. */
    private static final long serialVersionUID = -3715084806249419137L;

    /** Citation data for the resource(s). */
    private Citation citation;

    /** Brief narrative summary of the content of the resource(s). */
    private InternationalString abstracts;

    /** Summary of the intentions with which the resource(s) was developed. */
    private InternationalString purpose;

    /** Recognition of those who contributed to the resource(s). */
    private Collection<String> credits;

    /** Status of the resource(s). */
    private Collection<Progress> status;

    /**
     * Identification of, and means of communication with, person(s) and organizations(s) associated
     * with the resource(s).
     */
    private Collection<ResponsibleParty> pointOfContacts;

    /**
     * Provides information about the frequency of resource updates, and the scope of those updates.
     */
    private Collection<MaintenanceInformation> resourceMaintenance;

    /**
     * Provides a graphic that illustrates the resource(s) (should include a legend for the
     * graphic).
     */
    private Collection<BrowseGraphic> graphicOverviews;

    /** Provides a description of the format of the resource(s). */
    private Collection<Format> resourceFormat;

    /** Provides category keywords, their type, and reference source. */
    private Collection<Keywords> descriptiveKeywords;

    /**
     * Provides basic information about specific application(s) for which the resource(s) has/have
     * been or is being used by different users.
     */
    private Collection<Usage> resourceSpecificUsages;

    /** Provides information about constraints which apply to the resource(s). */
    private Collection<Constraints> resourceConstraints;

    /** Provides aggregate dataset information. */
    private Collection<AggregateInformation> aggregationInfo;

    /** Constructs an initially empty identification. */
    public IdentificationImpl() {}

    /**
     * Constructs a metadata entity initialized with the values from the specified metadata.
     *
     * @since 2.4
     */
    public IdentificationImpl(final Identification source) {
        super(source);
    }

    /** Creates an identification initialized to the specified values. */
    public IdentificationImpl(final Citation citation, final InternationalString abstracts) {
        setCitation(citation);
        setAbstract(abstracts);
    }

    /** Citation data for the resource(s). */
    @Override
    public Citation getCitation() {
        return citation;
    }

    /** Set the citation data for the resource(s). */
    public void setCitation(final Citation newValue) {
        checkWritePermission();
        citation = newValue;
    }

    /** Brief narrative summary of the content of the resource(s). */
    @Override
    public InternationalString getAbstract() {
        return abstracts;
    }

    /** Set a brief narrative summary of the content of the resource(s). */
    public void setAbstract(final InternationalString newValue) {
        checkWritePermission();
        abstracts = newValue;
    }

    /** Summary of the intentions with which the resource(s) was developed. */
    @Override
    public InternationalString getPurpose() {
        return purpose;
    }

    /** Set a summary of the intentions with which the resource(s) was developed. */
    public void setPurpose(final InternationalString newValue) {
        checkWritePermission();
        purpose = newValue;
    }

    /** Recognition of those who contributed to the resource(s). */
    @Override
    public Collection<String> getCredits() {
        return (credits = nonNullCollection(credits, String.class));
    }

    /** Set a recognition of those who contributed to the resource(s). */
    public void setCredits(final Collection<? extends String> newValues) {
        credits = copyCollection(newValues, credits, String.class);
    }

    /** Status of the resource(s). */
    @Override
    public Collection<Progress> getStatus() {
        return (status = nonNullCollection(status, Progress.class));
    }

    /** Set the status of the resource(s). */
    public void setStatus(final Collection<? extends Progress> newValues) {
        status = copyCollection(newValues, status, Progress.class);
    }

    /**
     * Identification of, and means of communication with, person(s) and organizations(s) associated
     * with the resource(s).
     */
    @Override
    public Collection<ResponsibleParty> getPointOfContacts() {
        return (pointOfContacts = nonNullCollection(pointOfContacts, ResponsibleParty.class));
    }

    /** Set the point of contacts. */
    public void setPointOfContacts(final Collection<? extends ResponsibleParty> newValues) {
        pointOfContacts = copyCollection(newValues, pointOfContacts, ResponsibleParty.class);
    }

    /**
     * Provides information about the frequency of resource updates, and the scope of those updates.
     */
    @Override
    public Collection<MaintenanceInformation> getResourceMaintenance() {
        return (resourceMaintenance =
                nonNullCollection(resourceMaintenance, MaintenanceInformation.class));
    }

    /** Set information about the frequency of resource updates, and the scope of those updates. */
    public void setResourceMaintenance(
            final Collection<? extends MaintenanceInformation> newValues) {
        resourceMaintenance =
                copyCollection(newValues, resourceMaintenance, MaintenanceInformation.class);
    }

    /**
     * Provides a graphic that illustrates the resource(s) (should include a legend for the
     * graphic).
     */
    @Override
    public Collection<BrowseGraphic> getGraphicOverviews() {
        return (graphicOverviews = nonNullCollection(graphicOverviews, BrowseGraphic.class));
    }

    /** Set a graphic that illustrates the resource(s). */
    public void setGraphicOverviews(final Collection<? extends BrowseGraphic> newValues) {
        graphicOverviews = copyCollection(newValues, graphicOverviews, BrowseGraphic.class);
    }

    /** Provides a description of the format of the resource(s). */
    @Override
    public Collection<Format> getResourceFormat() {
        return (resourceFormat = nonNullCollection(resourceFormat, Format.class));
    }

    /** Set a description of the format of the resource(s). */
    public void setResourceFormat(final Collection<? extends Format> newValues) {
        resourceFormat = copyCollection(newValues, resourceFormat, Format.class);
    }

    /** Provides category keywords, their type, and reference source. */
    @Override
    public Collection<Keywords> getDescriptiveKeywords() {
        return (descriptiveKeywords = nonNullCollection(descriptiveKeywords, Keywords.class));
    }

    /** Set category keywords, their type, and reference source. */
    public void setDescriptiveKeywords(final Collection<? extends Keywords> newValues) {
        descriptiveKeywords = copyCollection(newValues, descriptiveKeywords, Keywords.class);
    }

    /**
     * Provides basic information about specific application(s) for which the resource(s) has/have
     * been or is being used by different users.
     */
    @Override
    public Collection<Usage> getResourceSpecificUsages() {
        return (resourceSpecificUsages = nonNullCollection(resourceSpecificUsages, Usage.class));
    }

    /** Set basic information about specific application(s). */
    public void setResourceSpecificUsages(final Collection<? extends Usage> newValues) {
        resourceSpecificUsages = copyCollection(newValues, resourceSpecificUsages, Usage.class);
    }

    /** Provides information about constraints which apply to the resource(s). */
    @Override
    public Collection<Constraints> getResourceConstraints() {
        return (resourceConstraints = nonNullCollection(resourceConstraints, Constraints.class));
    }

    /** Set information about constraints which apply to the resource(s). */
    public void setResourceConstraints(final Collection<? extends Constraints> newValues) {
        resourceConstraints = copyCollection(newValues, resourceConstraints, Constraints.class);
    }

    /**
     * Provides aggregate dataset information.
     *
     * @since 2.4
     */
    @Override
    public Collection<AggregateInformation> getAggregationInfo() {
        return aggregationInfo = nonNullCollection(aggregationInfo, AggregateInformation.class);
    }

    /**
     * Sets aggregate dataset information.
     *
     * @since 2.4
     */
    public void setAggregationInfo(final Collection<? extends AggregateInformation> newValues) {
        aggregationInfo = copyCollection(newValues, aggregationInfo, AggregateInformation.class);
    }
}
