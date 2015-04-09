#!/usr/bin/perl -w

use strict;

while(1) {
  my @processes = `jps -m`;
  my @servers;
  my @names;

  my $process;
  foreach $process (@processes) {
    if($process =~ /(\d+).*ServerLauncher start (server\d)/) {
      push @servers, $1;
      push @names, $2;
    }
  }

  my $serverNum = int(rand @servers);
  my $serverId = $servers[$serverNum];
  my $serverName = $names[$serverNum];

  print "Oops, I broke $serverName - $serverId\n";
  kill 9, $serverId;
  sleep 30
}
