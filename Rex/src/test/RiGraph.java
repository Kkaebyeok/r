package test;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;

public class RiGraph {
	public static byte[] getIgraph() throws Exception {
		RConnection r = new RConnection();
		REXP rexp = null;
		
		r.parseAndEval("setwd('d:/workspaces/jsp-workspace/Rex/WebContent')");
		r.parseAndEval("library(rJava)");
		r.parseAndEval("library(DBI)");
		r.parseAndEval("library(RJDBC)");
		r.parseAndEval("library(igraph)");
		r.parseAndEval("drv <- JDBC('oracle.jdbc.driver.OracleDriver','c:oraclexe/app/oracle/product/11.2.0/server/jdbc/lib/ojdbc6.jar')");
		r.parseAndEval("conn <- dbConnect(drv, 'jdbc:oracle:thin:@localhost:1521:xe','hr','hr')");
		r.parseAndEval("df <- dbGetQuery(conn, 'select e2.first_name || e2.last_name temple, e1.first_name || e1.last_name boss from employees e1 right join employees e2 on e1.employee_id = e2.manager_id')");
		r.parseAndEval("g <-graph.data.frame(df,directed=T)");
		r.parseAndEval("plot(g,layout=layout.fruchterman.reingold,vertex.size=5,edge.arrow.size=0.5)");
		r.parseAndEval("savePlot('iGraph.png',type='png')");
		r.parseAndEval("graphics.off()");
		
		rexp = r.parseAndEval("readBin('iGraph.png','raw', 1024*1024)");
		byte[] arr = rexp.asBytes();
		r.close();
		return arr;
		
	}
}
