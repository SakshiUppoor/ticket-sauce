import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class LabelListController {
    @FXML
    private List<Label> labelList ;

    public void initialize() {
        int count = 1 ;
        for (Label label : labelList) {
            label.setText("Message " + (count++) );
        }
    }
}