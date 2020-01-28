package ch.hslu.swde.wda.ui;

/**
 * This interface provides a function which is needed to export TableView content to csv.
 *
 * @author Lukas Ingold
 */

public interface CSVExportable {

    /**
     * Returns object as a csv formated String
     *
     * @return comma separated Item as String
     */


    String toCSVString();
}
