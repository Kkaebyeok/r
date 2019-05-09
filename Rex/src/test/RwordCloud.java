package test;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;

public class RwordCloud {
	public static byte[] getImage() throws Exception{
		RConnection r = new RConnection();
		REXP rexp = null;
		
		r.parseAndEval("setwd('d:/workspaces/jsp-workspace/Rex/WebContent')");
		r.parseAndEval("library(KoNLP)");
		r.parseAndEval("library(wordcloud)");
		r.parseAndEval("library(RColorBrewer)");
		r.parseAndEval("useSejongDic()");
		r.parseAndEval("options(encoding='utf-8')");
		r.parseAndEval("data1 <- readLines('title.txt')");
		r.parseAndEval("data2 <- strsplit(data1,' ')");
		r.parseAndEval("data3 <- unlist(data2)");
		r.parseAndEval("write(data3,'retitle.txt')");
		r.parseAndEval("data4 <- read.table('retitle.txt')");
		r.parseAndEval("wordcount <- table(data4)");
		r.parseAndEval("head(sort(wordcount,decreasing=T),40)");
		r.parseAndEval("palete <-brewer.pal(9,'Set3')");
		r.parseAndEval("wordcloud(names(wordcount),freq=wordcount,scale=c(10,1),rot.per=0.25,min.freq=1,random.order=F,random.color=T,colors=palete)");
		r.parseAndEval("savePlot('wordcloud.png',type='png')");
		r.parseAndEval("graphics.off()");
		
		rexp = r.parseAndEval("readBin('wordcloud.png','raw', 1024*1024)");
		byte[] arr = rexp.asBytes();
		r.close();
		return arr;
	}
}
