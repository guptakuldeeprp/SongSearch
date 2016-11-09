# SongSearch
Notes to the reviewer:

-    Remove 'proxy_host' from strings.xml and set 'proxy_port' in integers.xml to 0 if you do not intend to use a proxy to connect to internet.
-    I do not know whether it is ok to share google-services.json publically. If not, let me know and I will remove it.
-    I have included a widget with the app which only displays the list of downloaded songs as of now. I am not able to get the onClick event on every item of a collection view in case of RemoteViews for the widget. Any help in setting that up will be highly appreciated
-    In the CapStone-1 project requirements pdf, I had mentioned of using OKHttp as my external library. But I did not find the need of it. Instead, I used Gson library for JSON parsing.
