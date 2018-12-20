import org.geotools.coverage.io.netcdf.NetCDFReader;
import org.geotools.data.DataSourceException;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.io.File;

public class NetCDFKMReader {

    public static void main(String[] args) throws DataSourceException, FactoryException {
        
        String directory = "/home/aaime/devel/gisData/harris/epsg_900914_geoserver_ticket/opt/harris/geoserver-indexing/NWS/NAMAK_TMP_ISBL";
        File file = new File(directory, "TMP_ISBL_20180515T120000_21600_e7b73ff1-5812-41a0-a3ff-09e5357a2b17.nc");
        NetCDFReader reader = new NetCDFReader(file, null);
        System.out.println(reader.getOriginalEnvelope().getCoordinateReferenceSystem());
        
        String wkt = "PROJCS[\"polar_stereographic\",GEOGCS[\"Unspecified datum based upon the International 1924 Authalic Sphere\",DATUM[\"Not_specified_based_on_International_1924_Authalic_Sphere\",SPHEROID[\"International 1924 Authalic Sphere\",6371228,0,AUTHORITY[\"EPSG\",\"7057\"]],AUTHORITY[\"EPSG\",\"6053\"]],PRIMEM[\"Greenwich\",0,AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\",0.0174532925199433,AUTHORITY[\"EPSG\",\"9122\"]],AUTHORITY[\"EPSG\",\"4053\"]],PROJECTION[\"Polar_Stereographic\"],PARAMETER[\"central_meridian\", -135.0],PARAMETER[\"latitude_of_origin\", 90.0],PARAMETER[\"scale_factor\", 0.9330127239227295],PARAMETER[\"false_easting\", 0.0],PARAMETER[\"false_northing\", 0.0],UNIT[\"km\", 1000.0],AXIS[\"Easting\", EAST],AXIS[\"Northing\", NORTH],AUTHORITY[\"EPSG\",\"900914\"]]";
        final CoordinateReferenceSystem crs = CRS.parseWKT(wkt);
        System.out.println(crs);
    }
}
