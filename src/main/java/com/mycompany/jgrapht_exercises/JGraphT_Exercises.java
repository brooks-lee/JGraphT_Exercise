/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jgrapht_exercises;

import java.io.File;
import java.util.Map;
import java.util.Scanner;


import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.ext.*;
import org.jgrapht.alg.NaiveLcaFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedPseudograph;
/**
 *
 * @author vaish
 */
public class JGraphT_Exercises {
    
    public static void main(String[] args) {
        // TODO code application logic here
        String filename, person1, person2;
        System.out.println("Enter path to the .dot file.");
        Scanner sc = new Scanner(System.in);
        filename = sc.nextLine();
        System.out.println("Enter person1");
        person1 = sc.nextLine();
        System.out.println("Enter person2");
        person2 = sc.nextLine();
        
        Graph<String, DefaultEdge> graph = new org.jgrapht.graph.DirectedPseudograph<>(DefaultEdge.class);
        
        VertexProvider<String> vp = new VertexProvider<String>()
        {
            @Override
            public String buildVertex(String label, Map<String, String> attributes)
            {
                return label;
            }
        };

        EdgeProvider<String, DefaultEdge> ep = new EdgeProvider<String, DefaultEdge>()
        {

            @Override
            public DefaultEdge buildEdge(String from, String to, String label, Map<String, String> attributes)
            {
                return graph.getEdgeFactory().createEdge(from, to);
            }

        };
        
        ComponentUpdater<String> vertexUpdater = new ComponentUpdater<String>() {
            @Override
            public void update(String t, Map<String, String> map) {
                map.put(t,t);
            }
        };
        
        DOTImporter<String, DefaultEdge> importer = new DOTImporter<String, DefaultEdge>(vp, ep, vertexUpdater);
        try{
            importer.importGraph(graph, new File(filename));
        }
        catch(ImportException e){
            System.out.print(e);
        }
     
        NaiveLcaFinder<String, DefaultEdge> graphFinder = new NaiveLcaFinder<>(graph);
        System.out.println(graphFinder.findLca(person1, person2)); 
        /*for(String lca: lcas)
        {
            System.out.println(lca);
        }*/
        
}

}
