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

Social posts from twitter, via
http://thinknook.com/twitter-sentiment-analysis-training-corpus-dataset-2012-09-22/       
 
------------------------------------------------------------

To use, install a copy of geode in this folder, with the name "geode"

    git clone https://github.com/upthewaterspout/geode-social-demo.git
    cd incubator-geode
    ./gradlew installDist

Install a copy of pulse in the compiled geode code

    cp pulse.war gemfire-assembly/build/install/geode/tools/Pulse/

Compile the server side code

    ./gradlew happy-gemfire-server:installDist

Start the servers

    bin/locator.sh
    bin/servers.sh

Start the chaos monkey

    bin/chaos.pl

Start the feed of posts

    cd happy-example
    gradle run

Start the analyzer process

    cd happy-example
    gradle run -Pargs=analyze

Start gfsh

    . bin/setenv.sh
    gfsh

Look at pulse

    gfsh> start pulse

To stop the feed or analyzer, press any key in the terminal

Kill everying

    bin/nuke.sh


