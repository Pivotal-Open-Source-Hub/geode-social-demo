------------------------------------------------------------
 Demo of a social network using gemfire
 Author: Dan Smith


 Words for sentiment analysis from 
    http://www.cs.uic.edu/~liub/FBS/sentiment-analysis.html

   Minqing Hu and Bing Liu. "Mining and Summarizing Customer Reviews." 
       Proceedings of the ACM SIGKDD International Conference on Knowledge 
       Discovery and Data Mining (KDD-2004), Aug 22-25, 2004, Seattle, 
       Washington, USA, 
   Bing Liu, Minqing Hu and Junsheng Cheng. "Opinion Observer: Analyzing 
       and Comparing Opinions on the Web." Proceedings of the 14th 
       International World Wide Web conference (WWW-2005), May 10-14, 
       2005, Chiba, Japan.
 
------------------------------------------------------------

To use, install a copy of geode in this folder, with the name "geode"

Compile the server side code
gradle happy-gemfire-server:installDist

Start the servers
bin/locator.sh
bin/servers.sh

Start the chaos monkey
bin/chaos.pl

Start the feed of posts
cd happy-example
gradle run

Start the analyzer process
gradle run -Pargs=analyze

Start gfsh
. bin/setenv.sh
gfsh

Stop the feed or analyzer
Press any key in the terminal

Kill everying
bin/nuke.sh


