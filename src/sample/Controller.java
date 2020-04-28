package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

import java.util.Stack;

public class Controller {
    private final AStarGraph model;
    private final AStarView view;

    public Controller(AStarGraph graphModel, AStarView viewAStar){
        this.model = graphModel;
        this.view = viewAStar;
        view.exitButton.setOnAction(e-> Platform.exit());
        // Add button to change heuristics
        EventHandler<ActionEvent> printRequestHandler= e->handlePrintRequest(view.startVertexCombobox.getValue(), view.endVertexCombobox.getValue(),view.heuristicsBox.getValue(), view.shortestPathTA);
        view.printButton.setOnAction(printRequestHandler);
    }


    public void handlePrintRequest(Vertex start, Vertex destination, String heuristics, TextArea TArea){
        model.A_Star(start,destination,heuristics);
        TArea.appendText("Chosen heuristic: " + heuristics+"\n");
        TArea.appendText("Found a path!\n");
        Vertex pvertex=destination;
        // to print path
        Stack<Vertex> Path = new Stack<>();
        int limit=0;
        while (pvertex!=null)
        {
            Path.push(pvertex);
            pvertex=pvertex.getPrev();
        }
        if(!Path.isEmpty())
            limit =Path.size();
        for(int i=0;i<limit-1;i++)
            TArea.appendText(Path.pop().getid() + " -> \n");
        if (limit>0)
            TArea.appendText(Path.pop().getid()+"\n");
    }
}
