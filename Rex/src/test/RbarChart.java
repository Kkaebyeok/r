package test;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;

public class RbarChart {
	public static byte[] getBarImg() throws Exception {
		RConnection r = new RConnection();
		REXP rexp = null;
		
		
		r.parseAndEval("setwd('d:/workspaces/jsp-workspace/Rex/WebContent')");
		r.parseAndEval("library(ggplot2)");
		r.parseAndEval("library(gridExtra)");
		r.parseAndEval("library(xlsx)");
		r.parseAndEval("library(KoNLP)");
		r.parseAndEval("file <- read.xlsx2('Theaters.xls',sheetIndex=1,startRow=1,header=TRUE, encoding ='UTF-8')");
		r.parseAndEval("p1 <- ggplot(file,aes(Borough,Open)) + geom_bar(stat='identity',fill='green',colour='black')");
		r.parseAndEval("p2 <- ggplot(file,aes(Borough,Screen)) + geom_bar(stat='identity',fill='green',colour='black')");
		r.parseAndEval("p3 <- ggplot(file,aes(Borough,Seats)) + geom_bar(stat='identity',fill='green',colour='black')");
		r.parseAndEval("grid.arrange(p1,p2,p3,nrow=3)");
		r.parseAndEval("savePlot('Bar.png',type='png')");
		r.parseAndEval("graphics.off()");
		
		rexp = r.parseAndEval("readBin('Bar.png','raw', 1024*1024)");
		byte[] arr = rexp.asBytes();
		r.close();
		return arr;
	}
}
