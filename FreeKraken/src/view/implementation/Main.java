package view.implementation;


import controller.DragAndDropMemory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.BinaryExpression;
import model.Configuration;
import model.Expression;
import model.KrakenTree;
import model.PrimaryExpression;
import model.Rule;
import model.UnaryExpression;
import view.implementation.graphicConfigurationParser.GraphicConfiguration;


public class Main extends Application {
	
	public static void createTicOrToe(KrakenTree tree) {
		Expression vide = new PrimaryExpression("VOID", "_");
        Expression x = new PrimaryExpression("X_MARKER", "X");
        Expression o = new PrimaryExpression("O_MARKER", "O");
        Expression sub_row = new BinaryExpression("ROW", vide.cloneExpression(), vide.cloneExpression());
        Expression row = new BinaryExpression("ROW", sub_row.cloneExpression(), vide.cloneExpression());
        
        Expression sub_board = new BinaryExpression("BOARD", row.cloneExpression(), row.cloneExpression());
        Expression board = new BinaryExpression("BOARD", sub_board.cloneExpression(), row.cloneExpression());
		
		tree.setRoot(board);
        
		Expression expr_C = new PrimaryExpression("EXPRESSION", "C");
        Expression sub_c_row = new BinaryExpression("ROW", expr_C.cloneExpression(), expr_C.cloneExpression());
        Expression c_row = new BinaryExpression("ROW", sub_c_row.cloneExpression(), expr_C.cloneExpression());
        Expression c_victory = new UnaryExpression("VICTORY", expr_C.cloneExpression());
        Expression b_victory = new UnaryExpression("VICTORY", new PrimaryExpression("EXPRESSION", "B"));
        Expression expr_A = new PrimaryExpression("EXPRESSION", "A");
        Expression board_A_victory = new BinaryExpression("BOARD", expr_A.cloneExpression(), b_victory.cloneExpression());
        Expression board_victory_A = new BinaryExpression("BOARD", b_victory.cloneExpression(), expr_A.cloneExpression());
        
        Expression expr_B = new PrimaryExpression("EXPRESSION", "B");
        Expression expr_D = new PrimaryExpression("EXPRESSION", "D");
        Expression expr_E = new PrimaryExpression("EXPRESSION", "E");
        Expression expr_F = new PrimaryExpression("EXPRESSION", "F");
        Expression expr_G = new PrimaryExpression("EXPRESSION", "G");
        Expression ab_row = new BinaryExpression("ROW", expr_A.cloneExpression(), expr_B.cloneExpression());
        Expression abc_row = new BinaryExpression("ROW", ab_row.cloneExpression(), expr_C.cloneExpression());
        Expression da_row = new BinaryExpression("ROW", expr_D.cloneExpression(), expr_A.cloneExpression());
        Expression dae_row = new BinaryExpression("ROW", da_row.cloneExpression(), expr_E.cloneExpression());
        Expression fg_row = new BinaryExpression("ROW", expr_F.cloneExpression(), expr_G.cloneExpression());
        Expression fga_row = new BinaryExpression("ROW", fg_row.cloneExpression(), expr_A.cloneExpression());
        
        Expression ad_row = new BinaryExpression("ROW", expr_A.cloneExpression(), expr_D.cloneExpression());
        Expression ade_row = new BinaryExpression("ROW", ad_row.cloneExpression(), expr_E.cloneExpression());
        Expression af_row = new BinaryExpression("ROW", expr_A.cloneExpression(), expr_F.cloneExpression());
        Expression afg_row = new BinaryExpression("ROW", af_row.cloneExpression(), expr_G.cloneExpression());
        Expression abc_ade_board = new BinaryExpression("BOARD", abc_row.cloneExpression(), ade_row.cloneExpression());
        Expression abc_ade_afg_board = new BinaryExpression("BOARD", abc_ade_board.cloneExpression(), afg_row.cloneExpression());
        
        Expression ba_row = new BinaryExpression("ROW", expr_B.cloneExpression(), expr_A.cloneExpression());
        Expression bac_row = new BinaryExpression("ROW", ba_row.cloneExpression(), expr_C.cloneExpression());
        Expression fa_row = new BinaryExpression("ROW", expr_F.cloneExpression(), expr_A.cloneExpression());
        Expression fag_row = new BinaryExpression("ROW", fa_row.cloneExpression(), expr_G.cloneExpression());
        Expression bac_dae_board = new BinaryExpression("BOARD", bac_row.cloneExpression(), dae_row.cloneExpression());
        Expression bac_dae_fag_board = new BinaryExpression("BOARD", bac_dae_board.cloneExpression(), fag_row.cloneExpression());
        
        Expression bc_row = new BinaryExpression("ROW", expr_B.cloneExpression(), expr_C.cloneExpression());
        Expression bca_row = new BinaryExpression("ROW", bc_row.cloneExpression(), expr_A.cloneExpression());
        Expression de_row = new BinaryExpression("ROW", expr_D.cloneExpression(), expr_E.cloneExpression());
        Expression dea_row = new BinaryExpression("ROW", de_row.cloneExpression(), expr_A.cloneExpression());
        Expression bca_dea_board = new BinaryExpression("BOARD", bca_row.cloneExpression(), dea_row.cloneExpression());
        Expression bca_dea_fga_board = new BinaryExpression("BOARD", bca_dea_board.cloneExpression(), fga_row.cloneExpression());
        
        Expression abc_dae_board = new BinaryExpression("BOARD", abc_row.cloneExpression(), dae_row.cloneExpression());
        Expression abc_dae_fga_board = new BinaryExpression("BOARD", abc_dae_board.cloneExpression(), fga_row.cloneExpression());
        
        Expression fga_dae_board = new BinaryExpression("BOARD", fga_row.cloneExpression(), dae_row.cloneExpression());
        Expression fga_dae_abc_board = new BinaryExpression("BOARD", fga_dae_board.cloneExpression(), abc_row.cloneExpression());
        
        Expression a_victory = new UnaryExpression("VICTORY", expr_A.cloneExpression());
		
        Configuration.rules.addRule("double_left_click", new Rule(abc_dae_fga_board.cloneExpression(), a_victory.cloneExpression()));
        Configuration.rules.addRule("double_left_click", new Rule(fga_dae_abc_board.cloneExpression(), a_victory.cloneExpression()));
        Configuration.rules.addRule("double_left_click", new Rule(abc_ade_afg_board.cloneExpression(), a_victory.cloneExpression()));
        Configuration.rules.addRule("double_left_click", new Rule(bac_dae_fag_board.cloneExpression(), a_victory.cloneExpression()));
        Configuration.rules.addRule("double_left_click", new Rule(bca_dea_fga_board.cloneExpression(), a_victory.cloneExpression()));
		Configuration.rules.addRule("double_left_click", new Rule(c_row.cloneExpression(), c_victory.cloneExpression()));
		Configuration.rules.addRule("double_left_click", new Rule(board_A_victory.cloneExpression(), b_victory.cloneExpression()));
		Configuration.rules.addRule("double_left_click", new Rule(board_victory_A.cloneExpression(), b_victory.cloneExpression()));
		
		Configuration.rules.addRule("left_click", new Rule(vide.cloneExpression(), x.cloneExpression()));
		Configuration.rules.addRule("left_click", new Rule(vide.cloneExpression(), o.cloneExpression()));
	}
	
	public static void createArithmetic(KrakenTree tree) {
	    Expression expr_a = new PrimaryExpression("LITTERAL", "a");
		Expression un = new PrimaryExpression("UN", "1");
		Expression zero = new PrimaryExpression("ZERO", "0");
		
		Expression plus_a0 = new BinaryExpression("PLUS", expr_a.cloneExpression(), zero.cloneExpression());

		Expression expr_A = new PrimaryExpression("EXPRESSION", "A");
		Expression expr_B = new PrimaryExpression("EXPRESSION", "B");
		Expression plus_AB = new BinaryExpression("PLUS", expr_A.cloneExpression(), expr_B.cloneExpression());
		Expression plus_BA = new BinaryExpression("PLUS", expr_B.cloneExpression(), expr_A.cloneExpression());
		Expression plus_A0 = new BinaryExpression("PLUS", expr_A.cloneExpression(), zero.cloneExpression());
		Expression plus_AA = new BinaryExpression("PLUS", expr_A.cloneExpression(), expr_A.cloneExpression());
		Expression plus_11 = new BinaryExpression("PLUS", un.cloneExpression(), un.cloneExpression());
		Expression parenthesis_11 = new UnaryExpression("PARENTHESIS", plus_11.cloneExpression());
		Expression fois_A2 = new BinaryExpression("FOIS", expr_A.cloneExpression(), parenthesis_11.cloneExpression());

		tree.setRoot(new BinaryExpression("PLUS", expr_a.cloneExpression(), expr_a.cloneExpression()));
		// A + B 	=(drag_and_drop)=> 		B + A
		// A + 0 	=(clic_gauche)=> 		A
		// A 		=(double_clic_gauche)=> A + 0
		
		// A*B <=> B*A
		Expression fois_AB = new BinaryExpression("FOIS", expr_A.cloneExpression(), expr_B.cloneExpression());
		Expression fois_BA = new BinaryExpression("FOIS", expr_B.cloneExpression(), expr_A.cloneExpression());
		Configuration.rules.addRule("drag_and_drop", new Rule(fois_AB.cloneExpression(), fois_BA.cloneExpression()) );
		Configuration.rules.addRule("drag_and_drop", new Rule(fois_BA.cloneExpression(), fois_AB.cloneExpression()) );
		
		// A <=> A*1
		Expression fois_A1 = new BinaryExpression("FOIS", expr_A.cloneExpression(), un.cloneExpression());
		Configuration.rules.addRule("double_left_click", new Rule(expr_A.cloneExpression(), fois_A1.cloneExpression()) );
		Configuration.rules.addRule("double_left_click", new Rule(expr_A.cloneExpression(), fois_A1.cloneExpression()) );
		
		
		
		Configuration.rules.addRule("drag_and_drop", new Rule(plus_AB.cloneExpression(), plus_BA.cloneExpression()) );
		Configuration.rules.addRule("left_click", new Rule(plus_A0.cloneExpression(), expr_A.cloneExpression()) );
		//Configuration.rules.addRule("left_click", new Rule(plus_AA.cloneExpression(), fois_A2.cloneExpression()) );
		Configuration.rules.addRule("double_left_click", new Rule(expr_A.cloneExpression() , plus_A0.cloneExpression()) );
		//Configuration.rules.addRule("double_left_click", new Rule(fois_A2.cloneExpression(), plus_AA.cloneExpression()) );
		//Configuration.rules.addRule("double_left_click", new Rule(expr_A.cloneExpression() , plus_A1.cloneExpression()) );
	}
	
	@Override
    public void start(Stage primaryStage) {
		
    	
    	
    	//=================== Partie de test A SUPPRIMER DANS L'AVENIR   =============================================
        StackPane center = new StackPane();
    	BorderPane root = new BorderPane();
    	GraphicConfiguration config = new GraphicConfiguration();
		KrakenTree tree = new KrakenTree(config);
		ControlTower tower = new ControlTower(tree, center, primaryStage);
    	DragAndDropMemory.memory = new DragAndDropMemory(tower);
		config.setControlTower(tower);
		
    	Expression a = new PrimaryExpression("LITTERAL", "a");
//		Expression b = new PrimaryExpression("LITTERAL", "b");
//		Expression c = new PrimaryExpression("LITTERAL", "c");
    	PrimaryGraphicExpression lit = new PrimaryGraphicExpression(a, tower);
//    	PrimaryGraphicExpression lit2 = new PrimaryGraphicExpression(b, tower);
//    	PrimaryGraphicExpression lit3 = new PrimaryGraphicExpression(c, tower);
    	
//    	Node operator = new Label("+");
//    	Node div = new Label("-");
//    	((Label) operator).getStyleClass().add("defaultFont");
//    	
//    	Node shapeOpen= new Label("sqrt(");
//    	Node shapeClose = new Label(")");
//    	((Label) shapeClose).getStyleClass().add("defaultFont");
//    	((Label) shapeOpen).getStyleClass().add("defaultFont");
//    	Orientation orientation = Orientation.HORIZONTAL;
//    	((Label) div).getStyleClass().add("defaultFont");;
//    	Orientation orientation1 = Orientation.VERTICAL;
    	
//    	lit2.setExpression("b");
//    	lit3.setExpression("c");
  
    	
    	
//    	BinaryGraphicExpression bin1 = new BinaryGraphicExpression(null, lit, lit2, operator, orientation, tower);
//    	UnaryGraphicExpression lol = new UnaryGraphicExpression(null, bin1, shapeOpen, shapeClose, orientation, tower);
//    	
//    	BinaryGraphicExpression bin2 = new BinaryGraphicExpression(null, lol, lit3, div, orientation1,tower);

    	
    	//=====================================================================================================

    	
    	// ajout des noeuds dans l'arbre du group
//    	center.getChildren().add(bin2);
    	center.getChildren().add(lit);
    	root.setCenter(center);

    	//cr�ation de la sc�ne et de ses propri�t�s par defaut
    	Scene scene = new Scene(root, 750, 500, Color.LIGHTGRAY);
    	
    	/*primaryStage.widthProperty().addListener(e -> {
            if(primaryStage.getWidth() < 600) {
            	scene.getStylesheets().clear();
            	scene.getStylesheets().add(getClass().getResource("cssSmall.fxml").toExternalForm()); 
            }else {
            	scene.getStylesheets().clear();
            	scene.getStylesheets().add(getClass().getResource("cssLarge.fxml").toExternalForm()); 
            }
        });*/
    	
        primaryStage.setTitle("New DragonBox");
        primaryStage.setScene(scene);
//        primaryStage.setFullScreen(true); // mettre en plein ecran responsive
        primaryStage.setMinHeight(200); // taille min de la fenetre
        primaryStage.setMinWidth(200);
        primaryStage.show();
        
        createTicOrToe(tree);
        
		tower.refreshWindow();
    }
    


	public static void main(String[] args) {
        launch(args);
    }
}