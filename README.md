<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="generator" content="Asciidoctor 1.5.3">
<meta name="author" content="박규태">
<title>Open Bank REST API Guide</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,300italic,400,400italic,600,600italic%7CNoto+Serif:400,400italic,700,700italic%7CDroid+Sans+Mono:400,700">
<style>
/* Asciidoctor default stylesheet | MIT License | http://asciidoctor.org */
/* Remove comment around @import statement below when using as a custom stylesheet */
/*@import "https://fonts.googleapis.com/css?family=Open+Sans:300,300italic,400,400italic,600,600italic%7CNoto+Serif:400,400italic,700,700italic%7CDroid+Sans+Mono:400,700";*/
article,aside,details,figcaption,figure,footer,header,hgroup,main,nav,section,summary{display:block}
audio,canvas,video{display:inline-block}
audio:not([controls]){display:none;height:0}
[hidden],template{display:none}
script{display:none!important}
html{font-family:sans-serif;-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}
body{margin:0}
a{background:transparent}
a:focus{outline:thin dotted}
a:active,a:hover{outline:0}
h1{font-size:2em;margin:.67em 0}
abbr[title]{border-bottom:1px dotted}
b,strong{font-weight:bold}
dfn{font-style:italic}
hr{-moz-box-sizing:content-box;box-sizing:content-box;height:0}
mark{background:#ff0;color:#000}
code,kbd,pre,samp{font-family:monospace;font-size:1em}
pre{white-space:pre-wrap}
q{quotes:"\201C" "\201D" "\2018" "\2019"}
small{font-size:80%}
sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}
sup{top:-.5em}
sub{bottom:-.25em}
img{border:0}
svg:not(:root){overflow:hidden}
figure{margin:0}
fieldset{border:1px solid silver;margin:0 2px;padding:.35em .625em .75em}
legend{border:0;padding:0}
button,input,select,textarea{font-family:inherit;font-size:100%;margin:0}
button,input{line-height:normal}
button,select{text-transform:none}
button,html input[type="button"],input[type="reset"],input[type="submit"]{-webkit-appearance:button;cursor:pointer}
button[disabled],html input[disabled]{cursor:default}
input[type="checkbox"],input[type="radio"]{box-sizing:border-box;padding:0}
input[type="search"]{-webkit-appearance:textfield;-moz-box-sizing:content-box;-webkit-box-sizing:content-box;box-sizing:content-box}
input[type="search"]::-webkit-search-cancel-button,input[type="search"]::-webkit-search-decoration{-webkit-appearance:none}
button::-moz-focus-inner,input::-moz-focus-inner{border:0;padding:0}
textarea{overflow:auto;vertical-align:top}
table{border-collapse:collapse;border-spacing:0}
*,*:before,*:after{-moz-box-sizing:border-box;-webkit-box-sizing:border-box;box-sizing:border-box}
html,body{font-size:100%}
body{background:#fff;color:rgba(0,0,0,.8);padding:0;margin:0;font-family:"Noto Serif","DejaVu Serif",serif;font-weight:400;font-style:normal;line-height:1;position:relative;cursor:auto}
a:hover{cursor:pointer}
img,object,embed{max-width:100%;height:auto}
object,embed{height:100%}
img{-ms-interpolation-mode:bicubic}
.left{float:left!important}
.right{float:right!important}
.text-left{text-align:left!important}
.text-right{text-align:right!important}
.text-center{text-align:center!important}
.text-justify{text-align:justify!important}
.hide{display:none}
body{-webkit-font-smoothing:antialiased}
img,object,svg{display:inline-block;vertical-align:middle}
textarea{height:auto;min-height:50px}
select{width:100%}
.center{margin-left:auto;margin-right:auto}
.spread{width:100%}
p.lead,.paragraph.lead>p,#preamble>.sectionbody>.paragraph:first-of-type p{font-size:1.21875em;line-height:1.6}
.subheader,.admonitionblock td.content>.title,.audioblock>.title,.exampleblock>.title,.imageblock>.title,.listingblock>.title,.literalblock>.title,.stemblock>.title,.openblock>.title,.paragraph>.title,.quoteblock>.title,table.tableblock>.title,.verseblock>.title,.videoblock>.title,.dlist>.title,.olist>.title,.ulist>.title,.qlist>.title,.hdlist>.title{line-height:1.45;color:#7a2518;font-weight:400;margin-top:0;margin-bottom:.25em}
div,dl,dt,dd,ul,ol,li,h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6,pre,form,p,blockquote,th,td{margin:0;padding:0;direction:ltr}
a{color:#2156a5;text-decoration:underline;line-height:inherit}
a:hover,a:focus{color:#1d4b8f}
a img{border:none}
p{font-family:inherit;font-weight:400;font-size:1em;line-height:1.6;margin-bottom:1.25em;text-rendering:optimizeLegibility}
p aside{font-size:.875em;line-height:1.35;font-style:italic}
h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6{font-family:"Open Sans","DejaVu Sans",sans-serif;font-weight:300;font-style:normal;color:#ba3925;text-rendering:optimizeLegibility;margin-top:1em;margin-bottom:.5em;line-height:1.0125em}
h1 small,h2 small,h3 small,#toctitle small,.sidebarblock>.content>.title small,h4 small,h5 small,h6 small{font-size:60%;color:#e99b8f;line-height:0}
h1{font-size:2.125em}
h2{font-size:1.6875em}
h3,#toctitle,.sidebarblock>.content>.title{font-size:1.375em}
h4,h5{font-size:1.125em}
h6{font-size:1em}
hr{border:solid #ddddd8;border-width:1px 0 0;clear:both;margin:1.25em 0 1.1875em;height:0}
em,i{font-style:italic;line-height:inherit}
strong,b{font-weight:bold;line-height:inherit}
small{font-size:60%;line-height:inherit}
code{font-family:"Droid Sans Mono","DejaVu Sans Mono",monospace;font-weight:400;color:rgba(0,0,0,.9)}
ul,ol,dl{font-size:1em;line-height:1.6;margin-bottom:1.25em;list-style-position:outside;font-family:inherit}
ul,ol,ul.no-bullet,ol.no-bullet{margin-left:1.5em}
ul li ul,ul li ol{margin-left:1.25em;margin-bottom:0;font-size:1em}
ul.square li ul,ul.circle li ul,ul.disc li ul{list-style:inherit}
ul.square{list-style-type:square}
ul.circle{list-style-type:circle}
ul.disc{list-style-type:disc}
ul.no-bullet{list-style:none}
ol li ul,ol li ol{margin-left:1.25em;margin-bottom:0}
dl dt{margin-bottom:.3125em;font-weight:bold}
dl dd{margin-bottom:1.25em}
abbr,acronym{text-transform:uppercase;font-size:90%;color:rgba(0,0,0,.8);border-bottom:1px dotted #ddd;cursor:help}
abbr{text-transform:none}
blockquote{margin:0 0 1.25em;padding:.5625em 1.25em 0 1.1875em;border-left:1px solid #ddd}
blockquote cite{display:block;font-size:.9375em;color:rgba(0,0,0,.6)}
blockquote cite:before{content:"\2014 \0020"}
blockquote cite a,blockquote cite a:visited{color:rgba(0,0,0,.6)}
blockquote,blockquote p{line-height:1.6;color:rgba(0,0,0,.85)}
@media only screen and (min-width:768px){h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6{line-height:1.2}
h1{font-size:2.75em}
h2{font-size:2.3125em}
h3,#toctitle,.sidebarblock>.content>.title{font-size:1.6875em}
h4{font-size:1.4375em}}
table{background:#fff;margin-bottom:1.25em;border:solid 1px #dedede}
table thead,table tfoot{background:#f7f8f7;font-weight:bold}
table thead tr th,table thead tr td,table tfoot tr th,table tfoot tr td{padding:.5em .625em .625em;font-size:inherit;color:rgba(0,0,0,.8);text-align:left}
table tr th,table tr td{padding:.5625em .625em;font-size:inherit;color:rgba(0,0,0,.8)}
table tr.even,table tr.alt,table tr:nth-of-type(even){background:#f8f8f7}
table thead tr th,table tfoot tr th,table tbody tr td,table tr td,table tfoot tr td{display:table-cell;line-height:1.6}
body{tab-size:4}
h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6{line-height:1.2;word-spacing:-.05em}
h1 strong,h2 strong,h3 strong,#toctitle strong,.sidebarblock>.content>.title strong,h4 strong,h5 strong,h6 strong{font-weight:400}
.clearfix:before,.clearfix:after,.float-group:before,.float-group:after{content:" ";display:table}
.clearfix:after,.float-group:after{clear:both}
*:not(pre)>code{font-size:.9375em;font-style:normal!important;letter-spacing:0;padding:.1em .5ex;word-spacing:-.15em;background-color:#f7f7f8;-webkit-border-radius:4px;border-radius:4px;line-height:1.45;text-rendering:optimizeSpeed}
pre,pre>code{line-height:1.45;color:rgba(0,0,0,.9);font-family:"Droid Sans Mono","DejaVu Sans Mono",monospace;font-weight:400;text-rendering:optimizeSpeed}
.keyseq{color:rgba(51,51,51,.8)}
kbd{font-family:"Droid Sans Mono","DejaVu Sans Mono",monospace;display:inline-block;color:rgba(0,0,0,.8);font-size:.65em;line-height:1.45;background-color:#f7f7f7;border:1px solid #ccc;-webkit-border-radius:3px;border-radius:3px;-webkit-box-shadow:0 1px 0 rgba(0,0,0,.2),0 0 0 .1em white inset;box-shadow:0 1px 0 rgba(0,0,0,.2),0 0 0 .1em #fff inset;margin:0 .15em;padding:.2em .5em;vertical-align:middle;position:relative;top:-.1em;white-space:nowrap}
.keyseq kbd:first-child{margin-left:0}
.keyseq kbd:last-child{margin-right:0}
.menuseq,.menu{color:rgba(0,0,0,.8)}
b.button:before,b.button:after{position:relative;top:-1px;font-weight:400}
b.button:before{content:"[";padding:0 3px 0 2px}
b.button:after{content:"]";padding:0 2px 0 3px}
p a>code:hover{color:rgba(0,0,0,.9)}
#header,#content,#footnotes,#footer{width:100%;margin-left:auto;margin-right:auto;margin-top:0;margin-bottom:0;max-width:62.5em;*zoom:1;position:relative;padding-left:.9375em;padding-right:.9375em}
#header:before,#header:after,#content:before,#content:after,#footnotes:before,#footnotes:after,#footer:before,#footer:after{content:" ";display:table}
#header:after,#content:after,#footnotes:after,#footer:after{clear:both}
#content{margin-top:1.25em}
#content:before{content:none}
#header>h1:first-child{color:rgba(0,0,0,.85);margin-top:2.25rem;margin-bottom:0}
#header>h1:first-child+#toc{margin-top:8px;border-top:1px solid #ddddd8}
#header>h1:only-child,body.toc2 #header>h1:nth-last-child(2){border-bottom:1px solid #ddddd8;padding-bottom:8px}
#header .details{border-bottom:1px solid #ddddd8;line-height:1.45;padding-top:.25em;padding-bottom:.25em;padding-left:.25em;color:rgba(0,0,0,.6);display:-ms-flexbox;display:-webkit-flex;display:flex;-ms-flex-flow:row wrap;-webkit-flex-flow:row wrap;flex-flow:row wrap}
#header .details span:first-child{margin-left:-.125em}
#header .details span.email a{color:rgba(0,0,0,.85)}
#header .details br{display:none}
#header .details br+span:before{content:"\00a0\2013\00a0"}
#header .details br+span.author:before{content:"\00a0\22c5\00a0";color:rgba(0,0,0,.85)}
#header .details br+span#revremark:before{content:"\00a0|\00a0"}
#header #revnumber{text-transform:capitalize}
#header #revnumber:after{content:"\00a0"}
#content>h1:first-child:not([class]){color:rgba(0,0,0,.85);border-bottom:1px solid #ddddd8;padding-bottom:8px;margin-top:0;padding-top:1rem;margin-bottom:1.25rem}
#toc{border-bottom:1px solid #efefed;padding-bottom:.5em}
#toc>ul{margin-left:.125em}
#toc ul.sectlevel0>li>a{font-style:italic}
#toc ul.sectlevel0 ul.sectlevel1{margin:.5em 0}
#toc ul{font-family:"Open Sans","DejaVu Sans",sans-serif;list-style-type:none}
#toc li{line-height:1.3334;margin-top:.3334em}
#toc a{text-decoration:none}
#toc a:active{text-decoration:underline}
#toctitle{color:#7a2518;font-size:1.2em}
@media only screen and (min-width:768px){#toctitle{font-size:1.375em}
body.toc2{padding-left:15em;padding-right:0}
#toc.toc2{margin-top:0!important;background-color:#f8f8f7;position:fixed;width:15em;left:0;top:0;border-right:1px solid #efefed;border-top-width:0!important;border-bottom-width:0!important;z-index:1000;padding:1.25em 1em;height:100%;overflow:auto}
#toc.toc2 #toctitle{margin-top:0;margin-bottom:.8rem;font-size:1.2em}
#toc.toc2>ul{font-size:.9em;margin-bottom:0}
#toc.toc2 ul ul{margin-left:0;padding-left:1em}
#toc.toc2 ul.sectlevel0 ul.sectlevel1{padding-left:0;margin-top:.5em;margin-bottom:.5em}
body.toc2.toc-right{padding-left:0;padding-right:15em}
body.toc2.toc-right #toc.toc2{border-right-width:0;border-left:1px solid #efefed;left:auto;right:0}}
@media only screen and (min-width:1280px){body.toc2{padding-left:20em;padding-right:0}
#toc.toc2{width:20em}
#toc.toc2 #toctitle{font-size:1.375em}
#toc.toc2>ul{font-size:.95em}
#toc.toc2 ul ul{padding-left:1.25em}
body.toc2.toc-right{padding-left:0;padding-right:20em}}
#content #toc{border-style:solid;border-width:1px;border-color:#e0e0dc;margin-bottom:1.25em;padding:1.25em;background:#f8f8f7;-webkit-border-radius:4px;border-radius:4px}
#content #toc>:first-child{margin-top:0}
#content #toc>:last-child{margin-bottom:0}
#footer{max-width:100%;background-color:rgba(0,0,0,.8);padding:1.25em}
#footer-text{color:rgba(255,255,255,.8);line-height:1.44}
.sect1{padding-bottom:.625em}
@media only screen and (min-width:768px){.sect1{padding-bottom:1.25em}}
.sect1+.sect1{border-top:1px solid #efefed}
#content h1>a.anchor,h2>a.anchor,h3>a.anchor,#toctitle>a.anchor,.sidebarblock>.content>.title>a.anchor,h4>a.anchor,h5>a.anchor,h6>a.anchor{position:absolute;z-index:1001;width:1.5ex;margin-left:-1.5ex;display:block;text-decoration:none!important;visibility:hidden;text-align:center;font-weight:400}
#content h1>a.anchor:before,h2>a.anchor:before,h3>a.anchor:before,#toctitle>a.anchor:before,.sidebarblock>.content>.title>a.anchor:before,h4>a.anchor:before,h5>a.anchor:before,h6>a.anchor:before{content:"\00A7";font-size:.85em;display:block;padding-top:.1em}
#content h1:hover>a.anchor,#content h1>a.anchor:hover,h2:hover>a.anchor,h2>a.anchor:hover,h3:hover>a.anchor,#toctitle:hover>a.anchor,.sidebarblock>.content>.title:hover>a.anchor,h3>a.anchor:hover,#toctitle>a.anchor:hover,.sidebarblock>.content>.title>a.anchor:hover,h4:hover>a.anchor,h4>a.anchor:hover,h5:hover>a.anchor,h5>a.anchor:hover,h6:hover>a.anchor,h6>a.anchor:hover{visibility:visible}
#content h1>a.link,h2>a.link,h3>a.link,#toctitle>a.link,.sidebarblock>.content>.title>a.link,h4>a.link,h5>a.link,h6>a.link{color:#ba3925;text-decoration:none}
#content h1>a.link:hover,h2>a.link:hover,h3>a.link:hover,#toctitle>a.link:hover,.sidebarblock>.content>.title>a.link:hover,h4>a.link:hover,h5>a.link:hover,h6>a.link:hover{color:#a53221}
.audioblock,.imageblock,.literalblock,.listingblock,.stemblock,.videoblock{margin-bottom:1.25em}
.admonitionblock td.content>.title,.audioblock>.title,.exampleblock>.title,.imageblock>.title,.listingblock>.title,.literalblock>.title,.stemblock>.title,.openblock>.title,.paragraph>.title,.quoteblock>.title,table.tableblock>.title,.verseblock>.title,.videoblock>.title,.dlist>.title,.olist>.title,.ulist>.title,.qlist>.title,.hdlist>.title{text-rendering:optimizeLegibility;text-align:left;font-family:"Noto Serif","DejaVu Serif",serif;font-size:1rem;font-style:italic}
table.tableblock>caption.title{white-space:nowrap;overflow:visible;max-width:0}
.paragraph.lead>p,#preamble>.sectionbody>.paragraph:first-of-type p{color:rgba(0,0,0,.85)}
table.tableblock #preamble>.sectionbody>.paragraph:first-of-type p{font-size:inherit}
.admonitionblock>table{border-collapse:separate;border:0;background:none;width:100%}
.admonitionblock>table td.icon{text-align:center;width:80px}
.admonitionblock>table td.icon img{max-width:none}
.admonitionblock>table td.icon .title{font-weight:bold;font-family:"Open Sans","DejaVu Sans",sans-serif;text-transform:uppercase}
.admonitionblock>table td.content{padding-left:1.125em;padding-right:1.25em;border-left:1px solid #ddddd8;color:rgba(0,0,0,.6)}
.admonitionblock>table td.content>:last-child>:last-child{margin-bottom:0}
.exampleblock>.content{border-style:solid;border-width:1px;border-color:#e6e6e6;margin-bottom:1.25em;padding:1.25em;background:#fff;-webkit-border-radius:4px;border-radius:4px}
.exampleblock>.content>:first-child{margin-top:0}
.exampleblock>.content>:last-child{margin-bottom:0}
.sidebarblock{border-style:solid;border-width:1px;border-color:#e0e0dc;margin-bottom:1.25em;padding:1.25em;background:#f8f8f7;-webkit-border-radius:4px;border-radius:4px}
.sidebarblock>:first-child{margin-top:0}
.sidebarblock>:last-child{margin-bottom:0}
.sidebarblock>.content>.title{color:#7a2518;margin-top:0;text-align:center}
.exampleblock>.content>:last-child>:last-child,.exampleblock>.content .olist>ol>li:last-child>:last-child,.exampleblock>.content .ulist>ul>li:last-child>:last-child,.exampleblock>.content .qlist>ol>li:last-child>:last-child,.sidebarblock>.content>:last-child>:last-child,.sidebarblock>.content .olist>ol>li:last-child>:last-child,.sidebarblock>.content .ulist>ul>li:last-child>:last-child,.sidebarblock>.content .qlist>ol>li:last-child>:last-child{margin-bottom:0}
.literalblock pre,.listingblock pre:not(.highlight),.listingblock pre[class="highlight"],.listingblock pre[class^="highlight "],.listingblock pre.CodeRay,.listingblock pre.prettyprint{background:#f7f7f8}
.sidebarblock .literalblock pre,.sidebarblock .listingblock pre:not(.highlight),.sidebarblock .listingblock pre[class="highlight"],.sidebarblock .listingblock pre[class^="highlight "],.sidebarblock .listingblock pre.CodeRay,.sidebarblock .listingblock pre.prettyprint{background:#f2f1f1}
.literalblock pre,.literalblock pre[class],.listingblock pre,.listingblock pre[class]{-webkit-border-radius:4px;border-radius:4px;word-wrap:break-word;padding:1em;font-size:.8125em}
.literalblock pre.nowrap,.literalblock pre[class].nowrap,.listingblock pre.nowrap,.listingblock pre[class].nowrap{overflow-x:auto;white-space:pre;word-wrap:normal}
@media only screen and (min-width:768px){.literalblock pre,.literalblock pre[class],.listingblock pre,.listingblock pre[class]{font-size:.90625em}}
@media only screen and (min-width:1280px){.literalblock pre,.literalblock pre[class],.listingblock pre,.listingblock pre[class]{font-size:1em}}
.literalblock.output pre{color:#f7f7f8;background-color:rgba(0,0,0,.9)}
.listingblock pre.highlightjs{padding:0}
.listingblock pre.highlightjs>code{padding:1em;-webkit-border-radius:4px;border-radius:4px}
.listingblock pre.prettyprint{border-width:0}
.listingblock>.content{position:relative}
.listingblock code[data-lang]:before{display:none;content:attr(data-lang);position:absolute;font-size:.75em;top:.425rem;right:.5rem;line-height:1;text-transform:uppercase;color:#999}
.listingblock:hover code[data-lang]:before{display:block}
.listingblock.terminal pre .command:before{content:attr(data-prompt);padding-right:.5em;color:#999}
.listingblock.terminal pre .command:not([data-prompt]):before{content:"$"}
table.pyhltable{border-collapse:separate;border:0;margin-bottom:0;background:none}
table.pyhltable td{vertical-align:top;padding-top:0;padding-bottom:0;line-height:1.45}
table.pyhltable td.code{padding-left:.75em;padding-right:0}
pre.pygments .lineno,table.pyhltable td:not(.code){color:#999;padding-left:0;padding-right:.5em;border-right:1px solid #ddddd8}
pre.pygments .lineno{display:inline-block;margin-right:.25em}
table.pyhltable .linenodiv{background:none!important;padding-right:0!important}
.quoteblock{margin:0 1em 1.25em 1.5em;display:table}
.quoteblock>.title{margin-left:-1.5em;margin-bottom:.75em}
.quoteblock blockquote,.quoteblock blockquote p{color:rgba(0,0,0,.85);font-size:1.15rem;line-height:1.75;word-spacing:.1em;letter-spacing:0;font-style:italic;text-align:justify}
.quoteblock blockquote{margin:0;padding:0;border:0}
.quoteblock blockquote:before{content:"\201c";float:left;font-size:2.75em;font-weight:bold;line-height:.6em;margin-left:-.6em;color:#7a2518;text-shadow:0 1px 2px rgba(0,0,0,.1)}
.quoteblock blockquote>.paragraph:last-child p{margin-bottom:0}
.quoteblock .attribution{margin-top:.5em;margin-right:.5ex;text-align:right}
.quoteblock .quoteblock{margin-left:0;margin-right:0;padding:.5em 0;border-left:3px solid rgba(0,0,0,.6)}
.quoteblock .quoteblock blockquote{padding:0 0 0 .75em}
.quoteblock .quoteblock blockquote:before{display:none}
.verseblock{margin:0 1em 1.25em 1em}
.verseblock pre{font-family:"Open Sans","DejaVu Sans",sans;font-size:1.15rem;color:rgba(0,0,0,.85);font-weight:300;text-rendering:optimizeLegibility}
.verseblock pre strong{font-weight:400}
.verseblock .attribution{margin-top:1.25rem;margin-left:.5ex}
.quoteblock .attribution,.verseblock .attribution{font-size:.9375em;line-height:1.45;font-style:italic}
.quoteblock .attribution br,.verseblock .attribution br{display:none}
.quoteblock .attribution cite,.verseblock .attribution cite{display:block;letter-spacing:-.025em;color:rgba(0,0,0,.6)}
.quoteblock.abstract{margin:0 0 1.25em 0;display:block}
.quoteblock.abstract blockquote,.quoteblock.abstract blockquote p{text-align:left;word-spacing:0}
.quoteblock.abstract blockquote:before,.quoteblock.abstract blockquote p:first-of-type:before{display:none}
table.tableblock{max-width:100%;border-collapse:separate}
table.tableblock td>.paragraph:last-child p>p:last-child,table.tableblock th>p:last-child,table.tableblock td>p:last-child{margin-bottom:0}
table.tableblock,th.tableblock,td.tableblock{border:0 solid #dedede}
table.grid-all th.tableblock,table.grid-all td.tableblock{border-width:0 1px 1px 0}
table.grid-all tfoot>tr>th.tableblock,table.grid-all tfoot>tr>td.tableblock{border-width:1px 1px 0 0}
table.grid-cols th.tableblock,table.grid-cols td.tableblock{border-width:0 1px 0 0}
table.grid-all *>tr>.tableblock:last-child,table.grid-cols *>tr>.tableblock:last-child{border-right-width:0}
table.grid-rows th.tableblock,table.grid-rows td.tableblock{border-width:0 0 1px 0}
table.grid-all tbody>tr:last-child>th.tableblock,table.grid-all tbody>tr:last-child>td.tableblock,table.grid-all thead:last-child>tr>th.tableblock,table.grid-rows tbody>tr:last-child>th.tableblock,table.grid-rows tbody>tr:last-child>td.tableblock,table.grid-rows thead:last-child>tr>th.tableblock{border-bottom-width:0}
table.grid-rows tfoot>tr>th.tableblock,table.grid-rows tfoot>tr>td.tableblock{border-width:1px 0 0 0}
table.frame-all{border-width:1px}
table.frame-sides{border-width:0 1px}
table.frame-topbot{border-width:1px 0}
th.halign-left,td.halign-left{text-align:left}
th.halign-right,td.halign-right{text-align:right}
th.halign-center,td.halign-center{text-align:center}
th.valign-top,td.valign-top{vertical-align:top}
th.valign-bottom,td.valign-bottom{vertical-align:bottom}
th.valign-middle,td.valign-middle{vertical-align:middle}
table thead th,table tfoot th{font-weight:bold}
tbody tr th{display:table-cell;line-height:1.6;background:#f7f8f7}
tbody tr th,tbody tr th p,tfoot tr th,tfoot tr th p{color:rgba(0,0,0,.8);font-weight:bold}
p.tableblock>code:only-child{background:none;padding:0}
p.tableblock{font-size:1em}
td>div.verse{white-space:pre}
ol{margin-left:1.75em}
ul li ol{margin-left:1.5em}
dl dd{margin-left:1.125em}
dl dd:last-child,dl dd:last-child>:last-child{margin-bottom:0}
ol>li p,ul>li p,ul dd,ol dd,.olist .olist,.ulist .ulist,.ulist .olist,.olist .ulist{margin-bottom:.625em}
ul.unstyled,ol.unnumbered,ul.checklist,ul.none{list-style-type:none}
ul.unstyled,ol.unnumbered,ul.checklist{margin-left:.625em}
ul.checklist li>p:first-child>.fa-square-o:first-child,ul.checklist li>p:first-child>.fa-check-square-o:first-child{width:1em;font-size:.85em}
ul.checklist li>p:first-child>input[type="checkbox"]:first-child{width:1em;position:relative;top:1px}
ul.inline{margin:0 auto .625em auto;margin-left:-1.375em;margin-right:0;padding:0;list-style:none;overflow:hidden}
ul.inline>li{list-style:none;float:left;margin-left:1.375em;display:block}
ul.inline>li>*{display:block}
.unstyled dl dt{font-weight:400;font-style:normal}
ol.arabic{list-style-type:decimal}
ol.decimal{list-style-type:decimal-leading-zero}
ol.loweralpha{list-style-type:lower-alpha}
ol.upperalpha{list-style-type:upper-alpha}
ol.lowerroman{list-style-type:lower-roman}
ol.upperroman{list-style-type:upper-roman}
ol.lowergreek{list-style-type:lower-greek}
.hdlist>table,.colist>table{border:0;background:none}
.hdlist>table>tbody>tr,.colist>table>tbody>tr{background:none}
td.hdlist1,td.hdlist2{vertical-align:top;padding:0 .625em}
td.hdlist1{font-weight:bold;padding-bottom:1.25em}
.literalblock+.colist,.listingblock+.colist{margin-top:-.5em}
.colist>table tr>td:first-of-type{padding:0 .75em;line-height:1}
.colist>table tr>td:last-of-type{padding:.25em 0}
.thumb,.th{line-height:0;display:inline-block;border:solid 4px #fff;-webkit-box-shadow:0 0 0 1px #ddd;box-shadow:0 0 0 1px #ddd}
.imageblock.left,.imageblock[style*="float: left"]{margin:.25em .625em 1.25em 0}
.imageblock.right,.imageblock[style*="float: right"]{margin:.25em 0 1.25em .625em}
.imageblock>.title{margin-bottom:0}
.imageblock.thumb,.imageblock.th{border-width:6px}
.imageblock.thumb>.title,.imageblock.th>.title{padding:0 .125em}
.image.left,.image.right{margin-top:.25em;margin-bottom:.25em;display:inline-block;line-height:0}
.image.left{margin-right:.625em}
.image.right{margin-left:.625em}
a.image{text-decoration:none;display:inline-block}
a.image object{pointer-events:none}
sup.footnote,sup.footnoteref{font-size:.875em;position:static;vertical-align:super}
sup.footnote a,sup.footnoteref a{text-decoration:none}
sup.footnote a:active,sup.footnoteref a:active{text-decoration:underline}
#footnotes{padding-top:.75em;padding-bottom:.75em;margin-bottom:.625em}
#footnotes hr{width:20%;min-width:6.25em;margin:-.25em 0 .75em 0;border-width:1px 0 0 0}
#footnotes .footnote{padding:0 .375em 0 .225em;line-height:1.3334;font-size:.875em;margin-left:1.2em;text-indent:-1.05em;margin-bottom:.2em}
#footnotes .footnote a:first-of-type{font-weight:bold;text-decoration:none}
#footnotes .footnote:last-of-type{margin-bottom:0}
#content #footnotes{margin-top:-.625em;margin-bottom:0;padding:.75em 0}
.gist .file-data>table{border:0;background:#fff;width:100%;margin-bottom:0}
.gist .file-data>table td.line-data{width:99%}
div.unbreakable{page-break-inside:avoid}
.big{font-size:larger}
.small{font-size:smaller}
.underline{text-decoration:underline}
.overline{text-decoration:overline}
.line-through{text-decoration:line-through}
.aqua{color:#00bfbf}
.aqua-background{background-color:#00fafa}
.black{color:#000}
.black-background{background-color:#000}
.blue{color:#0000bf}
.blue-background{background-color:#0000fa}
.fuchsia{color:#bf00bf}
.fuchsia-background{background-color:#fa00fa}
.gray{color:#606060}
.gray-background{background-color:#7d7d7d}
.green{color:#006000}
.green-background{background-color:#007d00}
.lime{color:#00bf00}
.lime-background{background-color:#00fa00}
.maroon{color:#600000}
.maroon-background{background-color:#7d0000}
.navy{color:#000060}
.navy-background{background-color:#00007d}
.olive{color:#606000}
.olive-background{background-color:#7d7d00}
.purple{color:#600060}
.purple-background{background-color:#7d007d}
.red{color:#bf0000}
.red-background{background-color:#fa0000}
.silver{color:#909090}
.silver-background{background-color:#bcbcbc}
.teal{color:#006060}
.teal-background{background-color:#007d7d}
.white{color:#bfbfbf}
.white-background{background-color:#fafafa}
.yellow{color:#bfbf00}
.yellow-background{background-color:#fafa00}
span.icon>.fa{cursor:default}
.admonitionblock td.icon [class^="fa icon-"]{font-size:2.5em;text-shadow:1px 1px 2px rgba(0,0,0,.5);cursor:default}
.admonitionblock td.icon .icon-note:before{content:"\f05a";color:#19407c}
.admonitionblock td.icon .icon-tip:before{content:"\f0eb";text-shadow:1px 1px 2px rgba(155,155,0,.8);color:#111}
.admonitionblock td.icon .icon-warning:before{content:"\f071";color:#bf6900}
.admonitionblock td.icon .icon-caution:before{content:"\f06d";color:#bf3400}
.admonitionblock td.icon .icon-important:before{content:"\f06a";color:#bf0000}
.conum[data-value]{display:inline-block;color:#fff!important;background-color:rgba(0,0,0,.8);-webkit-border-radius:100px;border-radius:100px;text-align:center;font-size:.75em;width:1.67em;height:1.67em;line-height:1.67em;font-family:"Open Sans","DejaVu Sans",sans-serif;font-style:normal;font-weight:bold}
.conum[data-value] *{color:#fff!important}
.conum[data-value]+b{display:none}
.conum[data-value]:after{content:attr(data-value)}
pre .conum[data-value]{position:relative;top:-.125em}
b.conum *{color:inherit!important}
.conum:not([data-value]):empty{display:none}
dt,th.tableblock,td.content,div.footnote{text-rendering:optimizeLegibility}
h1,h2,p,td.content,span.alt{letter-spacing:-.01em}
p strong,td.content strong,div.footnote strong{letter-spacing:-.005em}
p,blockquote,dt,td.content,span.alt{font-size:1.0625rem}
p{margin-bottom:1.25rem}
.sidebarblock p,.sidebarblock dt,.sidebarblock td.content,p.tableblock{font-size:1em}
.exampleblock>.content{background-color:#fffef7;border-color:#e0e0dc;-webkit-box-shadow:0 1px 4px #e0e0dc;box-shadow:0 1px 4px #e0e0dc}
.print-only{display:none!important}
@media print{@page{margin:1.25cm .75cm}
*{-webkit-box-shadow:none!important;box-shadow:none!important;text-shadow:none!important}
a{color:inherit!important;text-decoration:underline!important}
a.bare,a[href^="#"],a[href^="mailto:"]{text-decoration:none!important}
a[href^="http:"]:not(.bare):after,a[href^="https:"]:not(.bare):after{content:"(" attr(href) ")";display:inline-block;font-size:.875em;padding-left:.25em}
abbr[title]:after{content:" (" attr(title) ")"}
pre,blockquote,tr,img,object,svg{page-break-inside:avoid}
thead{display:table-header-group}
svg{max-width:100%}
p,blockquote,dt,td.content{font-size:1em;orphans:3;widows:3}
h2,h3,#toctitle,.sidebarblock>.content>.title{page-break-after:avoid}
#toc,.sidebarblock,.exampleblock>.content{background:none!important}
#toc{border-bottom:1px solid #ddddd8!important;padding-bottom:0!important}
.sect1{padding-bottom:0!important}
.sect1+.sect1{border:0!important}
#header>h1:first-child{margin-top:1.25rem}
body.book #header{text-align:center}
body.book #header>h1:first-child{border:0!important;margin:2.5em 0 1em 0}
body.book #header .details{border:0!important;display:block;padding:0!important}
body.book #header .details span:first-child{margin-left:0!important}
body.book #header .details br{display:block}
body.book #header .details br+span:before{content:none!important}
body.book #toc{border:0!important;text-align:left!important;padding:0!important;margin:0!important}
body.book #toc,body.book #preamble,body.book h1.sect0,body.book .sect1>h2{page-break-before:always}
.listingblock code[data-lang]:before{display:block}
#footer{background:none!important;padding:0 .9375em}
#footer-text{color:rgba(0,0,0,.6)!important;font-size:.9em}
.hide-on-print{display:none!important}
.print-only{display:block!important}
.hide-for-print{display:none!important}
.show-for-print{display:inherit!important}}
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<body class="book toc2 toc-left">
<div id="header">
<h1>Open Bank REST API Guide</h1>
<div class="details">
<span id="author" class="author">박규태</span><br>
</div>
</div>
<div id="content">
<h1 id="overview" class="sect0"><a class="link" href="#overview">개요</a></h1>
<div class="openblock partintro">
<div class="content">
본 프로젝트는 금융권에서 사용되는 계정계 업무를 오픈 소스화 하기 위한 프로젝트다.
기본적으로 Restful API를 제공하며, Restful을 이용한 Front-end를 기획 중이다.
Java 12, H2, Spring-Boot를 이용한다.
</div>
</div>
<div class="sect1">
<h2 id="overview-http-verbs"><a class="link" href="#overview-http-verbs">HTTP 동사</a></h2>
<div class="sectionbody">
<div class="paragraph">
<p>본 REST API에서 사용하는 HTTP 동사(verbs)는 가능한한 표준 HTTP와 REST 규약을 따릅니다.</p>
</div>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">동사</th>
<th class="tableblock halign-left valign-top">용례</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>GET</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">리소스를 가져올 때 사용</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>POST</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">새 리소스를 만들 때 사용</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>PUT</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">기존 리소스를 수정할 때 사용</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>PATCH</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">기존 리소스의 일부를 수정할 때 사용</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>DELETE</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">기존 리소스를 삭제할 떄 사용</p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect1">
<h2 id="overview-http-status-codes"><a class="link" href="#overview-http-status-codes">HTTP 상태 코드</a></h2>
<div class="sectionbody">
<div class="paragraph">
<p>본 REST API에서 사용하는 HTTP 상태 코드는 가능한한 표준 HTTP와 REST 규약을 따릅니다.</p>
</div>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">상태 코드</th>
<th class="tableblock halign-left valign-top">용례</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>200 OK</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">요청을 성공적으로 처리함</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>201 Created</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">새 리소스를 성공적으로 생성함. 응답의 <code>Location</code> 헤더에 해당 리소스의 URI가 담겨있다.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>204 No Content</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">기존 리소스를 성공적으로 수정함.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>400 Bad Request</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">잘못된 요청을 보낸 경우. 응답 본문에 더 오류에 대한 정보가 담겨있다.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>404 Not Found</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">요청한 리소스가 없음.</p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect1">
<h2 id="overview-errors"><a class="link" href="#overview-errors">오류</a></h2>
<div class="sectionbody">
<div class="paragraph">
<p>에러 응답이 발생했을 때 (상태 코드 &gt;= 400), 본문에 해당 문제를 기술한 JSON 객체가 담겨있다. 에러 객체는 다음의 구조를 따른다.</p>
</div>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 33%;">
<col style="width: 33%;">
<col style="width: 33%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>message</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">error message</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>status</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">result</p></td>
</tr>
</tbody>
</table>
<div class="paragraph">
<p>예를 들어, 잘못된 값으로 출금을 요청 했을 때 다음과 같은 <code>500 INTERNAL_SERVER_ERROR</code> 응답을 받는다.</p>
</div>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 500 Internal Server Error
Content-Type: application/json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 86

{
  "message" : "출금가능금액 부족",
  "status" : "INTERNAL_SERVER_ERROR"
}</code></pre>
</div>
</div>
</div>
</div>
<div class="sect1">
<h2 id="overview-hypermedia"><a class="link" href="#overview-hypermedia">하이퍼미디어</a></h2>
<div class="sectionbody">
<div class="paragraph">
<p>본 REST API는 하이퍼미디어와 사용하며 응답에 담겨있는 리소스는 다른 리소스에 대한 링크를 가지고 있다.
응답은 <a href="http://stateless.co/hal_specification.html">Hypertext Application from resource to resource. Language (HAL)</a> 형식을 따른다.
링크는 `_links`라는 키로 제공한다. 본 API의 사용자(클라이언트)는 URI를 직접 생성하지 않아야 하며, 리소스에서 제공하는 링크를 사용해야 한다.</p>
</div>
</div>
</div>
<h1 id="resources" class="sect0"><a class="link" href="#resources">리소스</a></h1>
<div class="sect1">
<h2 id="resources-index"><a class="link" href="#resources-index">인덱스</a></h2>
<div class="sectionbody">
<div class="paragraph">
<p>인덱스는 서비스 진입점을 제공한다.</p>
</div>
<div class="sect2">
<h3 id="resources-index-access"><a class="link" href="#resources-index-access">인덱스 조회</a></h3>
<div class="paragraph">
<p><code>GET</code> 요청을 사용하여 인덱스에 접근할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-index-access_curl_request"><a class="link" href="#resources-index-access_curl_request">Example request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/api' -i -X GET</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-index-access_response_body"><a class="link" href="#resources-index-access_response_body">Response body</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code>{
  "_links" : {
    "regular-account" : {
      "href" : "http://localhost:8080/api/account/regular"
    },
    "branches" : {
      "href" : "http://localhost:8080/api/branch"
    },
    "customers" : {
      "href" : "http://localhost:8080/api/customer"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-index-access"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-index-access_http_response"><a class="link" href="#resources-index-access_http_response">Example response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Link: &lt;/docs/index.html&gt;; rel="profile"
Content-Length: 365

{
  "_links" : {
    "regular-account" : {
      "href" : "http://localhost:8080/api/account/regular"
    },
    "branches" : {
      "href" : "http://localhost:8080/api/branch"
    },
    "customers" : {
      "href" : "http://localhost:8080/api/customer"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-index-access"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-index-access_links"><a class="link" href="#resources-index-access_links">Links</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>regular-account</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to regular account list.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>customers</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to customer list.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>branches</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to branch list.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>profile</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
</div>
<div class="sect1">
<h2 id="resources-customers"><a class="link" href="#resources-customers">고객</a></h2>
<div class="sectionbody">
<div class="paragraph">
<p>고객 리소스는 고객정보를 만들거나 조회할 때 사용한다.</p>
</div>
<div class="sect2">
<h3 id="resources-customer-dto"><a class="link" href="#resources-customer-dto">고객 목록 조회</a></h3>
<div class="paragraph">
<p><code>GET</code> 요청을 사용하여 서비스의 모든 고객정보를 조회할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-customer-dto_response_fields"><a class="link" href="#resources-customer-dto_response_fields">Response fields</a></h4>
<div class="paragraph">
<p>Snippet response-fields not found for operation::customer-dto</p>
</div>
</div>
<div class="sect3">
<h4 id="resources-customer-dto_curl_request"><a class="link" href="#resources-customer-dto_curl_request">Example request</a></h4>
<div class="paragraph">
<p>Snippet curl-request not found for operation::customer-dto</p>
</div>
</div>
<div class="sect3">
<h4 id="resources-customer-dto_http_response"><a class="link" href="#resources-customer-dto_http_response">Example response</a></h4>
<div class="paragraph">
<p>Snippet http-response not found for operation::customer-dto</p>
</div>
</div>
<div class="sect3">
<h4 id="resources-customer-dto_links"><a class="link" href="#resources-customer-dto_links">Links</a></h4>
<div class="paragraph">
<p>Snippet links not found for operation::customer-dto</p>
</div>
</div>
</div>
<div class="sect2">
<h3 id="resources-customer-create"><a class="link" href="#resources-customer-create">고객정보 생성</a></h3>
<div class="paragraph">
<p><code>POST</code> 요청을 사용해서 새 고객정보를 만들 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-customer-create_request_fields"><a class="link" href="#resources-customer-create_request_fields">Request fields</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 33%;">
<col style="width: 33%;">
<col style="width: 33%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>name</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Name of new customer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>email</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Email of new customer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>nation</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Nation of new customer</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-customer-create_curl_request"><a class="link" href="#resources-customer-create_curl_request">Example request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/api/customer' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da' \
    -H 'Accept: application/hal+json' \
    -d '{
  "name" : "홍길동",
  "email" : "masterhong@gil.dong.com",
  "nation" : "KOREA"
}'</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-customer-create_http_request"><a class="link" href="#resources-customer-create_http_request">HTTP request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">POST /api/customer HTTP/1.1
Content-Type: application/json;charset=UTF-8
Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da
Accept: application/hal+json
Host: localhost:8080
Content-Length: 91

{
  "name" : "홍길동",
  "email" : "masterhong@gil.dong.com",
  "nation" : "KOREA"
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-customer-create_request_headers"><a class="link" href="#resources-customer-create_request_headers">Request headers</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Accept</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">accept header</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">content type header</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-customer-create_http_response"><a class="link" href="#resources-customer-create_http_response">Example response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 201 Created
Location: /api/customer/219
Content-Type: application/hal+json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Link: &lt;/docs/index.html&gt;; rel="profile"
Content-Length: 586

{
  "id" : 219,
  "name" : "홍길동",
  "email" : "masterhong@gil.dong.com",
  "nation" : "KOREA",
  "regBranchName" : "인터넷뱅킹",
  "mngBranchName" : "인터넷뱅킹",
  "regEmployeeName" : "인터넷사용자",
  "regDateTime" : "2020-01-04T20:27:45.970197",
  "_links" : {
    "self" : {
      "href" : "/api/customer/219"
    },
    "customer-update" : {
      "href" : "/api/customer/219"
    },
    "customer-list" : {
      "href" : "/api/customer"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-customers-create"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-customer-create_response_headers"><a class="link" href="#resources-customer-create_response_headers">Response headers</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Location</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Location header</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">HAL/JSON type content type</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-customer-create_response_fields"><a class="link" href="#resources-customer-create_response_fields">Response fields</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 33%;">
<col style="width: 33%;">
<col style="width: 33%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>id</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">identifier of new customer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>name</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">name of new customer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>email</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">email of new customer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>nation</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">nation of new customer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>regDateTime</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">registration date of new customer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>regBranchName</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">registration branch of new customer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>mngBranchName</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">management branch of new customer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>regEmployeeName</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">registration employee of new customer.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.self.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.customer-list.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to query customers.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.customer-update.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to update existing customer.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.profile.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-customer-create_links"><a class="link" href="#resources-customer-create_links">Links</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>self</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>customer-list</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to query customers</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>customer-update</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to update an existing customer</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>profile</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect2">
<h3 id="resources-customer-get"><a class="link" href="#resources-customer-get">고객 조회</a></h3>
<div class="paragraph">
<p><code>Get</code> 요청을 사용해서 기존 고객정보 하나를 조회할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-customer-get_request_parameters"><a class="link" href="#resources-customer-get_request_parameters">Request parameters</a></h4>
<div class="paragraph">
<p>Snippet request-parameters not found for operation::get-customer</p>
</div>
</div>
<div class="sect3">
<h4 id="resources-customer-get_curl_request"><a class="link" href="#resources-customer-get_curl_request">Example request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/api/customer/188' -i -X GET</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-customer-get_http_request"><a class="link" href="#resources-customer-get_http_request">HTTP request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">GET /api/customer/188 HTTP/1.1
Host: localhost:8080</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-customer-get_http_response"><a class="link" href="#resources-customer-get_http_response">Example response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Link: &lt;/docs/index.html&gt;; rel="profile"
Content-Length: 579

{
  "id" : 188,
  "name" : "고객 500",
  "email" : "customer500@gmail.com",
  "nation" : "KOREA",
  "regBranchName" : "인터넷뱅킹",
  "mngBranchName" : "인터넷뱅킹",
  "regEmployeeName" : "인터넷사용자",
  "regDateTime" : "2020-01-04T20:27:44.825209",
  "_links" : {
    "self" : {
      "href" : "/api/customer/188"
    },
    "customer-update" : {
      "href" : "/api/customer/188"
    },
    "customer-list" : {
      "href" : "/api/customer"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-branch-get"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-customer-get_links"><a class="link" href="#resources-customer-get_links">Links</a></h4>
<div class="paragraph">
<p>Snippet links not found for operation::get-customer</p>
</div>
</div>
</div>
<div class="sect2">
<h3 id="resources-customers-update"><a class="link" href="#resources-customers-update">고객정보 수정</a></h3>
<div class="paragraph">
<p><code>PUT</code> 요청을 사용해서 기존 고객정보를 수정할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-customers-update_request_fields"><a class="link" href="#resources-customers-update_request_fields">Request fields</a></h4>
<div class="paragraph">
<p>Snippet request-fields not found for operation::update-customer</p>
</div>
</div>
<div class="sect3">
<h4 id="resources-customers-update_curl_request"><a class="link" href="#resources-customers-update_curl_request">Example request</a></h4>
<div class="paragraph">
<p>Snippet curl-request not found for operation::update-customer</p>
</div>
</div>
<div class="sect3">
<h4 id="resources-customers-update_http_response"><a class="link" href="#resources-customers-update_http_response">Example response</a></h4>
<div class="paragraph">
<p>Snippet http-response not found for operation::update-customer</p>
</div>
</div>
<div class="sect3">
<h4 id="resources-customers-update_links"><a class="link" href="#resources-customers-update_links">Links</a></h4>
<div class="paragraph">
<p>Snippet links not found for operation::update-customer</p>
</div>
</div>
</div>
<div class="sect2">
<h3 id="resources-branches-create"><a class="link" href="#resources-branches-create">지점 생성</a></h3>
<div class="paragraph">
<p><code>POST</code> 요청을 사용해서 새 지점을 만들 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-branches-create_request_fields"><a class="link" href="#resources-branches-create_request_fields">Request fields</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 33%;">
<col style="width: 33%;">
<col style="width: 33%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>name</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Name of new branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>businessNumber</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">business number of new branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>taxOfficeCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">tax office code of new branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>telNumber</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">telephone number of new branch</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-branches-create_curl_request"><a class="link" href="#resources-branches-create_curl_request">Example request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/api/branch' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da' \
    -H 'Accept: application/hal+json' \
    -d '{
  "name" : "테스트지점",
  "businessNumber" : "123-12-12345",
  "taxOfficeCode" : "112",
  "telNumber" : "02-1234-1234"
}'</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-branches-create_http_request"><a class="link" href="#resources-branches-create_http_request">HTTP request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">POST /api/branch HTTP/1.1
Content-Type: application/json;charset=UTF-8
Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da
Accept: application/hal+json
Host: localhost:8080
Content-Length: 133

{
  "name" : "테스트지점",
  "businessNumber" : "123-12-12345",
  "taxOfficeCode" : "112",
  "telNumber" : "02-1234-1234"
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-branches-create_request_headers"><a class="link" href="#resources-branches-create_request_headers">Request headers</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Accept</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">accept header</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">content type header</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-branches-create_http_response"><a class="link" href="#resources-branches-create_http_response">Example response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 201 Created
Location: /api/branch/127
Content-Type: application/hal+json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Link: &lt;/docs/index.html&gt;; rel="profile"
Content-Length: 523

{
  "id" : 127,
  "name" : "테스트지점",
  "businessNumber" : "123-12-12345",
  "taxOfficeCode" : "112",
  "telNumber" : "02-1234-1234",
  "regDateTime" : "2020-01-04T20:27:43.935587",
  "branchType" : "지점",
  "_links" : {
    "self" : {
      "href" : "/api/branch/127"
    },
    "update-branch" : {
      "href" : "/api/branch/127"
    },
    "query-branches" : {
      "href" : "/api/branch"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-branches-create"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-branches-create_response_headers"><a class="link" href="#resources-branches-create_response_headers">Response headers</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Location</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Location header</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">HAL/JSON type content type</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-branches-create_response_fields"><a class="link" href="#resources-branches-create_response_fields">Response fields</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 33%;">
<col style="width: 33%;">
<col style="width: 33%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>id</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">identifier of new branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>name</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">name of new branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>businessNumber</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">business number of new branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>taxOfficeCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">tax office code of new branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>telNumber</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">telephone number of new branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>regDateTime</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">registration date of new branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>branchType</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">branch type of new branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.self.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.query-branches.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to query branches.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.update-branch.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to update existing branch.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.profile.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-branches-create_links"><a class="link" href="#resources-branches-create_links">Links</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>self</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>query-branches</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to query branches</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>update-branch</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to update an existing branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>profile</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect2">
<h3 id="resources-branches-dto"><a class="link" href="#resources-branches-dto">지점 목록 조회</a></h3>
<div class="paragraph">
<p><code>GET</code> 요청을 사용하여 서비스의 모든 지점정보를 조회할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-branches-dto_response_fields"><a class="link" href="#resources-branches-dto_response_fields">Response fields</a></h4>
<div class="paragraph">
<p>Snippet response-fields not found for operation::get-branches</p>
</div>
</div>
<div class="sect3">
<h4 id="resources-branches-dto_curl_request"><a class="link" href="#resources-branches-dto_curl_request">Example request</a></h4>
<div class="paragraph">
<p>Snippet curl-request not found for operation::get-branches</p>
</div>
</div>
<div class="sect3">
<h4 id="resources-branches-dto_http_response"><a class="link" href="#resources-branches-dto_http_response">Example response</a></h4>
<div class="paragraph">
<p>Snippet http-response not found for operation::get-branches</p>
</div>
</div>
<div class="sect3">
<h4 id="resources-branches-dto_links"><a class="link" href="#resources-branches-dto_links">Links</a></h4>
<div class="paragraph">
<p>Snippet links not found for operation::get-branches</p>
</div>
</div>
</div>
<div class="sect2">
<h3 id="resources-branch-get"><a class="link" href="#resources-branch-get">지점 조회</a></h3>
<div class="paragraph">
<p><code>Get</code> 요청을 사용해서 기존 지점 하나를 조회할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-branch-get_curl_request"><a class="link" href="#resources-branch-get_curl_request">Example request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/api/branch/124' -i -X GET</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-branch-get_http_request"><a class="link" href="#resources-branch-get_http_request">HTTP request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">GET /api/branch/124 HTTP/1.1
Host: localhost:8080</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-branch-get_http_response"><a class="link" href="#resources-branch-get_http_response">Example response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Link: &lt;/docs/index.html&gt;; rel="profile"
Content-Length: 515

{
  "id" : 124,
  "name" : "지점이름200",
  "businessNumber" : "bzNum200",
  "taxOfficeCode" : "00200",
  "telNumber" : "02-1234-1234",
  "regDateTime" : "2020-01-04T20:27:42.99035",
  "branchType" : "지점",
  "_links" : {
    "self" : {
      "href" : "/api/branch/124"
    },
    "update-branch" : {
      "href" : "/api/branch/124"
    },
    "query-branches" : {
      "href" : "/api/branch"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-branch-get"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-branch-get_response_body"><a class="link" href="#resources-branch-get_response_body">Response body</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code>{
  "id" : 124,
  "name" : "지점이름200",
  "businessNumber" : "bzNum200",
  "taxOfficeCode" : "00200",
  "telNumber" : "02-1234-1234",
  "regDateTime" : "2020-01-04T20:27:42.99035",
  "branchType" : "지점",
  "_links" : {
    "self" : {
      "href" : "/api/branch/124"
    },
    "update-branch" : {
      "href" : "/api/branch/124"
    },
    "query-branches" : {
      "href" : "/api/branch"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-branch-get"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-branch-get_response_fields"><a class="link" href="#resources-branch-get_response_fields">Response fields</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 33%;">
<col style="width: 33%;">
<col style="width: 33%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>id</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">identifier of branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>name</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">name of branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>businessNumber</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">businessNumber of branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>taxOfficeCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">taxOfficeCode of branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>telNumber</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">telNumber date of branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>regDateTime</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">regDateTime branch of branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>branchType</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">branchType branch of branch</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.self.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.update-branch.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to first.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.query-branches.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to prev.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.profile.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-branch-get_response_headers"><a class="link" href="#resources-branch-get_response_headers">Response headers</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">HAL/JSON type content type</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-branch-get_links"><a class="link" href="#resources-branch-get_links">Links</a></h4>
<div class="paragraph">
<p>Snippet links not found for operation::get-branch</p>
</div>
</div>
</div>
<div class="sect2">
<h3 id="resources-branch-update"><a class="link" href="#resources-branch-update">지점정보 수정</a></h3>
<div class="paragraph">
<p><code>PUT</code> 요청을 사용해서 기존 지점정보를 수정할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-branch-update_request_fields"><a class="link" href="#resources-branch-update_request_fields">Request fields</a></h4>
<div class="paragraph">
<p>Snippet request-fields not found for operation::update-branch</p>
</div>
</div>
<div class="sect3">
<h4 id="resources-branch-update_curl_request"><a class="link" href="#resources-branch-update_curl_request">Example request</a></h4>
<div class="paragraph">
<p>Snippet curl-request not found for operation::update-branch</p>
</div>
</div>
<div class="sect3">
<h4 id="resources-branch-update_http_response"><a class="link" href="#resources-branch-update_http_response">Example response</a></h4>
<div class="paragraph">
<p>Snippet http-response not found for operation::update-branch</p>
</div>
</div>
<div class="sect3">
<h4 id="resources-branch-update_links"><a class="link" href="#resources-branch-update_links">Links</a></h4>
<div class="paragraph">
<p>Snippet links not found for operation::update-branch</p>
</div>
</div>
</div>
<div class="sect2">
<h3 id="resources-accounts-create"><a class="link" href="#resources-accounts-create">계좌 생성</a></h3>
<div class="paragraph">
<p><code>POST</code> 요청을 사용해서 새 계좌를 만들 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-accounts-create_request_fields"><a class="link" href="#resources-accounts-create_request_fields">Request fields</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 33%;">
<col style="width: 33%;">
<col style="width: 33%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>productCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">product code of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>regDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Registration Date of new account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>taxationCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">way to tax in interest</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-accounts-create_curl_request"><a class="link" href="#resources-accounts-create_curl_request">Example request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/api/account/regular' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da' \
    -H 'Accept: application/hal+json' \
    -d '{
  "productCode" : "130999",
  "regDate" : "20191214",
  "taxationCode" : "REGULAR"
}'</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-accounts-create_http_request"><a class="link" href="#resources-accounts-create_http_request">HTTP request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">POST /api/account/regular HTTP/1.1
Content-Type: application/json;charset=UTF-8
Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da
Accept: application/hal+json
Host: localhost:8080
Content-Length: 90

{
  "productCode" : "130999",
  "regDate" : "20191214",
  "taxationCode" : "REGULAR"
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-accounts-create_request_headers"><a class="link" href="#resources-accounts-create_request_headers">Request headers</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Accept</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">accept header</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">content type header</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-accounts-create_http_response"><a class="link" href="#resources-accounts-create_http_response">Example response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 201 Created
Location: /api/account/regular/13100017
Content-Type: application/hal+json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Link: &lt;/docs/index.html&gt;; rel="profile"
Content-Length: 824

{
  "productCode" : "130999",
  "productName" : "온라인 보통예금",
  "subjectCode" : "REGULAR",
  "basicRate" : 1.2,
  "regDate" : "20191214",
  "taxationCode" : "REGULAR",
  "accountNum" : "13100017",
  "closeDate" : null,
  "lastIntsDt" : "20191213",
  "balance" : 0,
  "accountStatusCode" : "ACTIVE",
  "_links" : {
    "self" : {
      "href" : "/api/account/regular/13100017"
    },
    "deposit" : {
      "href" : "/api/account/regular/13100017/deposit"
    },
    "withdraw" : {
      "href" : "/api/account/regular/13100017/withdraw"
    },
    "close" : {
      "href" : "/api/account/regular/13100017/close"
    },
    "query-accounts" : {
      "href" : "/api/account/regular"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-accounts-create"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-accounts-create_response_headers"><a class="link" href="#resources-accounts-create_response_headers">Response headers</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Location</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Location header</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">HAL/JSON type content type</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-accounts-create_response_fields"><a class="link" href="#resources-accounts-create_response_fields">Response fields</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 33%;">
<col style="width: 33%;">
<col style="width: 33%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>accountNum</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">identification number of new account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>basicRate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">interest rate of this account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>regDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Registration Date of new account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>closeDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Null</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Close Date of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>taxationCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">way to tax in interest</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>lastIntsDt</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">the last calculated date of account interest</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>balance</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">balance of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>productCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">product code of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>productName</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">name of product</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>subjectCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">code of account type</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>accountStatusCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">status of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.self.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.query-accounts.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to query accountes.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.deposit.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to deposit existing account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.withdraw.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to withdraw existing account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.close.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to close existing account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.profile.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-accounts-create_links"><a class="link" href="#resources-accounts-create_links">Links</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>self</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>query-accounts</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to query accounts</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>deposit</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to deposit an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>withdraw</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to withdraw an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>close</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to close an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>profile</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect2">
<h3 id="resources-accounts-dto"><a class="link" href="#resources-accounts-dto">계좌 목록 조회</a></h3>
<div class="paragraph">
<p><code>GET</code> 요청을 사용하여 서비스의 모든 계좌정보를 조회할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-accounts-dto_response_fields"><a class="link" href="#resources-accounts-dto_response_fields">Response fields</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 33%;">
<col style="width: 33%;">
<col style="width: 33%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0].accountNum</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">identification number of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0].basicRate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">interest rate of this account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0].regDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Registration Date of new account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0].closeDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Null</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Close Date of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0].taxationCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">way to tax in interest</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0].lastIntsDt</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">the last calculated date of account interest</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0].balance</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">balance of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0].productCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">product code of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0].productName</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">name of product</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0].subjectCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">code of account type</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0].accountStatusCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">status of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0]._links.self.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.self.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.profile.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.first.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to first.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.prev.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to prev.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.next.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to next.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.last.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to last.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.create-account.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to open account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>page.size</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">size of one page.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>page.totalElements</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">amount of datas.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>page.totalPages</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">amount of pages.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>page.number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">current page number.</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-accounts-dto_curl_request"><a class="link" href="#resources-accounts-dto_curl_request">Example request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/api/account/regular?page=1&amp;size=5&amp;sort=accountNum%2CDESC' -i -X GET \
    -H 'Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da'</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-accounts-dto_http_response"><a class="link" href="#resources-accounts-dto_http_response">Example response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Link: &lt;/docs/index.html&gt;; rel="profile"
Content-Length: 3364

{
  "_embedded" : {
    "responseList" : [ {
      "productCode" : "130999",
      "productName" : "온라인 보통예금",
      "subjectCode" : "REGULAR",
      "basicRate" : 1.2,
      "regDate" : "20191215",
      "taxationCode" : "REGULAR",
      "accountNum" : "13100051",
      "closeDate" : null,
      "lastIntsDt" : "20191214",
      "balance" : 0,
      "accountStatusCode" : "ACTIVE",
      "_links" : {
        "self" : {
          "href" : "/api/account/regular/13100051"
        }
      }
    }, {
      "productCode" : "130999",
      "productName" : "온라인 보통예금",
      "subjectCode" : "REGULAR",
      "basicRate" : 1.2,
      "regDate" : "20191215",
      "taxationCode" : "REGULAR",
      "accountNum" : "13100048",
      "closeDate" : null,
      "lastIntsDt" : "20191214",
      "balance" : 0,
      "accountStatusCode" : "ACTIVE",
      "_links" : {
        "self" : {
          "href" : "/api/account/regular/13100048"
        }
      }
    }, {
      "productCode" : "130999",
      "productName" : "온라인 보통예금",
      "subjectCode" : "REGULAR",
      "basicRate" : 1.2,
      "regDate" : "20191215",
      "taxationCode" : "REGULAR",
      "accountNum" : "13100045",
      "closeDate" : null,
      "lastIntsDt" : "20191214",
      "balance" : 0,
      "accountStatusCode" : "ACTIVE",
      "_links" : {
        "self" : {
          "href" : "/api/account/regular/13100045"
        }
      }
    }, {
      "productCode" : "130999",
      "productName" : "온라인 보통예금",
      "subjectCode" : "REGULAR",
      "basicRate" : 1.2,
      "regDate" : "20191215",
      "taxationCode" : "REGULAR",
      "accountNum" : "13100042",
      "closeDate" : null,
      "lastIntsDt" : "20191214",
      "balance" : 0,
      "accountStatusCode" : "ACTIVE",
      "_links" : {
        "self" : {
          "href" : "/api/account/regular/13100042"
        }
      }
    }, {
      "productCode" : "130999",
      "productName" : "온라인 보통예금",
      "subjectCode" : "REGULAR",
      "basicRate" : 1.2,
      "regDate" : "20191215",
      "taxationCode" : "REGULAR",
      "accountNum" : "13100039",
      "closeDate" : null,
      "lastIntsDt" : "20191214",
      "balance" : 0,
      "accountStatusCode" : "ACTIVE",
      "_links" : {
        "self" : {
          "href" : "/api/account/regular/13100039"
        }
      }
    } ]
  },
  "_links" : {
    "first" : {
      "href" : "http://localhost:8080/api/account/regular?page=0&amp;size=5&amp;sort=accountNum,desc"
    },
    "prev" : {
      "href" : "http://localhost:8080/api/account/regular?page=0&amp;size=5&amp;sort=accountNum,desc"
    },
    "self" : {
      "href" : "http://localhost:8080/api/account/regular?page=1&amp;size=5&amp;sort=accountNum,desc"
    },
    "next" : {
      "href" : "http://localhost:8080/api/account/regular?page=2&amp;size=5&amp;sort=accountNum,desc"
    },
    "last" : {
      "href" : "http://localhost:8080/api/account/regular?page=3&amp;size=5&amp;sort=accountNum,desc"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-accounts-list"
    },
    "create-account" : {
      "href" : "/api/account/regular"
    }
  },
  "page" : {
    "size" : 5,
    "totalElements" : 18,
    "totalPages" : 4,
    "number" : 1
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-accounts-dto_links"><a class="link" href="#resources-accounts-dto_links">Links</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>first</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to first page</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>prev</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to prev page</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>self</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>next</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to next page</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>last</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to last page</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>create-account</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to open account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>profile</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect2">
<h3 id="resources-account-get"><a class="link" href="#resources-account-get">계좌 정보 조회</a></h3>
<div class="paragraph">
<p><code>Get</code> 요청을 사용해서 계좌 정보 하나를 조회할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-account-get_curl_request"><a class="link" href="#resources-account-get_curl_request">Example request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/api/account/regular/13100074' -i -X GET \
    -H 'Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da'</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-account-get_http_request"><a class="link" href="#resources-account-get_http_request">HTTP request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">GET /api/account/regular/13100074 HTTP/1.1
Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da
Host: localhost:8080</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-account-get_http_response"><a class="link" href="#resources-account-get_http_response">Example response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Link: &lt;/docs/index.html&gt;; rel="profile"
Content-Length: 821

{
  "productCode" : "130999",
  "productName" : "온라인 보통예금",
  "subjectCode" : "REGULAR",
  "basicRate" : 1.2,
  "regDate" : "20191215",
  "taxationCode" : "REGULAR",
  "accountNum" : "13100074",
  "closeDate" : null,
  "lastIntsDt" : "20191214",
  "balance" : 0,
  "accountStatusCode" : "ACTIVE",
  "_links" : {
    "self" : {
      "href" : "/api/account/regular/13100074"
    },
    "deposit" : {
      "href" : "/api/account/regular/13100074/deposit"
    },
    "withdraw" : {
      "href" : "/api/account/regular/13100074/withdraw"
    },
    "close" : {
      "href" : "/api/account/regular/13100074/close"
    },
    "query-accounts" : {
      "href" : "/api/account/regular"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-accounts-get"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-account-get_response_body"><a class="link" href="#resources-account-get_response_body">Response body</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code>{
  "productCode" : "130999",
  "productName" : "온라인 보통예금",
  "subjectCode" : "REGULAR",
  "basicRate" : 1.2,
  "regDate" : "20191215",
  "taxationCode" : "REGULAR",
  "accountNum" : "13100074",
  "closeDate" : null,
  "lastIntsDt" : "20191214",
  "balance" : 0,
  "accountStatusCode" : "ACTIVE",
  "_links" : {
    "self" : {
      "href" : "/api/account/regular/13100074"
    },
    "deposit" : {
      "href" : "/api/account/regular/13100074/deposit"
    },
    "withdraw" : {
      "href" : "/api/account/regular/13100074/withdraw"
    },
    "close" : {
      "href" : "/api/account/regular/13100074/close"
    },
    "query-accounts" : {
      "href" : "/api/account/regular"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-accounts-get"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-account-get_response_fields"><a class="link" href="#resources-account-get_response_fields">Response fields</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 33%;">
<col style="width: 33%;">
<col style="width: 33%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>accountNum</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">identification number of new account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>basicRate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">interest rate of this account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>regDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Registration Date of new account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>closeDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Null</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Close Date of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>taxationCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">way to tax in interest</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>lastIntsDt</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">the last calculated date of account interest</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>balance</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">balance of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>productCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">product code of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>productName</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">name of product</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>subjectCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">code of account type</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>accountStatusCode</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">status of account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.self.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.query-accounts.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to query accountes.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.deposit.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to deposit existing account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.withdraw.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to withdraw existing account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.close.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to close existing account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.profile.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-account-get_response_headers"><a class="link" href="#resources-account-get_response_headers">Response headers</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">HAL/JSON type content type</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-account-get_links"><a class="link" href="#resources-account-get_links">Links</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>self</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>query-accounts</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to query accounts</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>deposit</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to deposit an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>withdraw</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to withdraw an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>close</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to close an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>profile</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect2">
<h3 id="resources-account-deposit"><a class="link" href="#resources-account-deposit">계좌 입금 요청</a></h3>
<div class="paragraph">
<p><code>PUT</code> 요청을 사용해서 해당 계좌에 입금을 할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-account-deposit_request_body"><a class="link" href="#resources-account-deposit_request_body">Request body</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code>{
  "tradeDate" : "20191215",
  "amount" : 100000
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-account-deposit_curl_request"><a class="link" href="#resources-account-deposit_curl_request">Example request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/api/account/regular/13100013/deposit' -i -X PUT \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da' \
    -d '{
  "tradeDate" : "20191215",
  "amount" : 100000
}'</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-account-deposit_http_response"><a class="link" href="#resources-account-deposit_http_response">Example response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Link: &lt;/docs/index.html&gt;; rel="profile"
Content-Length: 677

{
  "srno" : 2,
  "tradeDate" : "20191215",
  "bzDate" : "20200104",
  "amount" : 100000,
  "blncBefore" : 0,
  "blncAfter" : 100000,
  "tradeCd" : "DEPOSIT",
  "_links" : {
    "self" : {
      "href" : "/api/account/regular/13100013/deposit"
    },
    "deposit" : {
      "href" : "/api/account/regular/13100013/deposit"
    },
    "withdraw" : {
      "href" : "/api/account/regular/13100013/withdraw"
    },
    "close" : {
      "href" : "/api/account/regular/13100013/close"
    },
    "query-accounts" : {
      "href" : "/api/account/regular"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-accounts-deposit"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-account-deposit_links"><a class="link" href="#resources-account-deposit_links">Links</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>self</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>query-accounts</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to query accounts</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>deposit</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to deposit an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>withdraw</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to withdraw an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>close</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to close an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>profile</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect2">
<h3 id="resources-account-withdraw"><a class="link" href="#resources-account-withdraw">계좌 출금 요청</a></h3>
<div class="paragraph">
<p><code>PUT</code> 요청을 사용해서 해당 계좌에 출금을 할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-account-withdraw_request_body"><a class="link" href="#resources-account-withdraw_request_body">Request body</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code>{
  "tradeDate" : "20191215",
  "amount" : 30000
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-account-withdraw_curl_request"><a class="link" href="#resources-account-withdraw_curl_request">Example request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/api/account/regular/13100069/withdraw' -i -X PUT \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da' \
    -d '{
  "tradeDate" : "20191215",
  "amount" : 30000
}'</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-account-withdraw_http_response"><a class="link" href="#resources-account-withdraw_http_response">Example response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Link: &lt;/docs/index.html&gt;; rel="profile"
Content-Length: 683

{
  "srno" : 3,
  "tradeDate" : "20191215",
  "bzDate" : "20200104",
  "amount" : 30000,
  "blncBefore" : 100000,
  "blncAfter" : 70000,
  "tradeCd" : "WITHDRAW",
  "_links" : {
    "self" : {
      "href" : "/api/account/regular/13100069/withdraw"
    },
    "deposit" : {
      "href" : "/api/account/regular/13100069/deposit"
    },
    "withdraw" : {
      "href" : "/api/account/regular/13100069/withdraw"
    },
    "close" : {
      "href" : "/api/account/regular/13100069/close"
    },
    "query-accounts" : {
      "href" : "/api/account/regular"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-accounts-withdraw"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-account-withdraw_links"><a class="link" href="#resources-account-withdraw_links">Links</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>self</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>query-accounts</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to query accounts</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>deposit</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to deposit an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>withdraw</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to withdraw an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>close</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to close an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>profile</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect2">
<h3 id="resources-get-trade-dto"><a class="link" href="#resources-get-trade-dto">계좌 거래내역 조회</a></h3>
<div class="paragraph">
<p><code>GET</code> 요청을 사용해서 해당 계좌의 거래내역을 조회 할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-get-trade-dto_curl_request"><a class="link" href="#resources-get-trade-dto_curl_request">Example request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/api/account/regular/13100077/trade?page=1&amp;size=5&amp;sort=srno%2CDESC' -i -X GET \
    -H 'Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da'</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-get-trade-dto_http_request"><a class="link" href="#resources-get-trade-dto_http_request">HTTP request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">GET /api/account/regular/13100077/trade?page=1&amp;size=5&amp;sort=srno%2CDESC HTTP/1.1
Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da
Host: localhost:8080</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-get-trade-dto_http_response"><a class="link" href="#resources-get-trade-dto_http_response">Example response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Link: &lt;/docs/index.html&gt;; rel="profile"
Content-Length: 2189

{
  "_embedded" : {
    "responseList" : [ {
      "srno" : 10,
      "tradeDate" : "20191024",
      "bzDate" : "20200104",
      "amount" : 9,
      "blncBefore" : 36,
      "blncAfter" : 45,
      "tradeCd" : "DEPOSIT"
    }, {
      "srno" : 9,
      "tradeDate" : "20191023",
      "bzDate" : "20200104",
      "amount" : 8,
      "blncBefore" : 28,
      "blncAfter" : 36,
      "tradeCd" : "DEPOSIT"
    }, {
      "srno" : 8,
      "tradeDate" : "20191022",
      "bzDate" : "20200104",
      "amount" : 7,
      "blncBefore" : 21,
      "blncAfter" : 28,
      "tradeCd" : "DEPOSIT"
    }, {
      "srno" : 7,
      "tradeDate" : "20191021",
      "bzDate" : "20200104",
      "amount" : 6,
      "blncBefore" : 15,
      "blncAfter" : 21,
      "tradeCd" : "DEPOSIT"
    }, {
      "srno" : 6,
      "tradeDate" : "20191020",
      "bzDate" : "20200104",
      "amount" : 5,
      "blncBefore" : 10,
      "blncAfter" : 15,
      "tradeCd" : "DEPOSIT"
    } ]
  },
  "_links" : {
    "first" : {
      "href" : "http://localhost:8080/api/account/regular/13100077/trade?page=0&amp;size=5&amp;sort=srno,desc"
    },
    "prev" : {
      "href" : "http://localhost:8080/api/account/regular/13100077/trade?page=0&amp;size=5&amp;sort=srno,desc"
    },
    "self" : {
      "href" : "http://localhost:8080/api/account/regular/13100077/trade?page=1&amp;size=5&amp;sort=srno,desc"
    },
    "next" : {
      "href" : "http://localhost:8080/api/account/regular/13100077/trade?page=2&amp;size=5&amp;sort=srno,desc"
    },
    "last" : {
      "href" : "http://localhost:8080/api/account/regular/13100077/trade?page=2&amp;size=5&amp;sort=srno,desc"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-trade-list"
    },
    "deposit" : {
      "href" : "/api/account/regular/13100077/deposit"
    },
    "withdraw" : {
      "href" : "/api/account/regular/13100077/withdraw"
    },
    "close" : {
      "href" : "/api/account/regular/13100077/close"
    },
    "query-accounts" : {
      "href" : "/api/account/regular"
    }
  },
  "page" : {
    "size" : 5,
    "totalElements" : 15,
    "totalPages" : 3,
    "number" : 1
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-get-trade-dto_response_body"><a class="link" href="#resources-get-trade-dto_response_body">Response body</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code>{
  "_embedded" : {
    "responseList" : [ {
      "srno" : 10,
      "tradeDate" : "20191024",
      "bzDate" : "20200104",
      "amount" : 9,
      "blncBefore" : 36,
      "blncAfter" : 45,
      "tradeCd" : "DEPOSIT"
    }, {
      "srno" : 9,
      "tradeDate" : "20191023",
      "bzDate" : "20200104",
      "amount" : 8,
      "blncBefore" : 28,
      "blncAfter" : 36,
      "tradeCd" : "DEPOSIT"
    }, {
      "srno" : 8,
      "tradeDate" : "20191022",
      "bzDate" : "20200104",
      "amount" : 7,
      "blncBefore" : 21,
      "blncAfter" : 28,
      "tradeCd" : "DEPOSIT"
    }, {
      "srno" : 7,
      "tradeDate" : "20191021",
      "bzDate" : "20200104",
      "amount" : 6,
      "blncBefore" : 15,
      "blncAfter" : 21,
      "tradeCd" : "DEPOSIT"
    }, {
      "srno" : 6,
      "tradeDate" : "20191020",
      "bzDate" : "20200104",
      "amount" : 5,
      "blncBefore" : 10,
      "blncAfter" : 15,
      "tradeCd" : "DEPOSIT"
    } ]
  },
  "_links" : {
    "first" : {
      "href" : "http://localhost:8080/api/account/regular/13100077/trade?page=0&amp;size=5&amp;sort=srno,desc"
    },
    "prev" : {
      "href" : "http://localhost:8080/api/account/regular/13100077/trade?page=0&amp;size=5&amp;sort=srno,desc"
    },
    "self" : {
      "href" : "http://localhost:8080/api/account/regular/13100077/trade?page=1&amp;size=5&amp;sort=srno,desc"
    },
    "next" : {
      "href" : "http://localhost:8080/api/account/regular/13100077/trade?page=2&amp;size=5&amp;sort=srno,desc"
    },
    "last" : {
      "href" : "http://localhost:8080/api/account/regular/13100077/trade?page=2&amp;size=5&amp;sort=srno,desc"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-trade-list"
    },
    "deposit" : {
      "href" : "/api/account/regular/13100077/deposit"
    },
    "withdraw" : {
      "href" : "/api/account/regular/13100077/withdraw"
    },
    "close" : {
      "href" : "/api/account/regular/13100077/close"
    },
    "query-accounts" : {
      "href" : "/api/account/regular"
    }
  },
  "page" : {
    "size" : 5,
    "totalElements" : 15,
    "totalPages" : 3,
    "number" : 1
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-get-trade-dto_response_fields"><a class="link" href="#resources-get-trade-dto_response_fields">Response fields</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 33%;">
<col style="width: 33%;">
<col style="width: 33%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0]srno</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">serial number of trade</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0]tradeDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">trade date as a request</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0]bzDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">real trade date or system date</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0]amount</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">trade amount</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0]blncBefore</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">the balance before trade</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0]blncAfter</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">the balance after trade</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.responseList[0]tradeCd</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">type of trade</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.first.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to first.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.prev.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to prev.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.next.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to next.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.last.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to last.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>page.size</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">size of one page.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>page.totalElements</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">amount of datas.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>page.totalPages</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">amount of pages.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>page.number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">current page number.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.self.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.query-accounts.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to query accountes.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.deposit.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to deposit existing account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.withdraw.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to withdraw existing account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.close.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to close existing account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.profile.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-get-trade-dto_response_headers"><a class="link" href="#resources-get-trade-dto_response_headers">Response headers</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">HAL/JSON type content type</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-get-trade-dto_links"><a class="link" href="#resources-get-trade-dto_links">Links</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>first</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to first page</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>prev</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to prev page</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>self</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>next</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to next page</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>last</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to last page</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>profile</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>query-accounts</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to query accounts</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>deposit</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to deposit an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>withdraw</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to withdraw an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>close</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to close an existing account</p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect2">
<h3 id="resources-interest-calculate"><a class="link" href="#resources-interest-calculate">이자 금액 예상 조회</a></h3>
<div class="paragraph">
<p><code>GET</code> 요청을 사용해서 해당 계좌의 이자계산을 조회 할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-interest-calculate_curl_request"><a class="link" href="#resources-interest-calculate_curl_request">Example request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/api/interest/13100260/20200101' -i -X GET \
    -H 'Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da'</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-interest-calculate_http_request"><a class="link" href="#resources-interest-calculate_http_request">HTTP request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">GET /api/interest/13100260/20200101 HTTP/1.1
Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da
Host: localhost:8080</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-interest-calculate_http_response"><a class="link" href="#resources-interest-calculate_http_response">Example response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Link: &lt;/docs/index.html&gt;; rel="profile"
Content-Length: 1377

{
  "accountNum" : "13100260",
  "lastIntsDt" : "20161231",
  "balance" : 1530000,
  "fromDate" : "20170101",
  "toDate" : "20191231",
  "basicRate" : 1.2,
  "interest" : 42720,
  "periodType" : "DAILY",
  "details" : [ {
    "id" : 267,
    "fromDate" : "20190101",
    "toDate" : "20191231",
    "interestRate" : 1.2,
    "taxRate" : 0.0,
    "balance" : 1530000,
    "months" : 0,
    "days" : 365,
    "interest" : 18360.0,
    "tax" : 0.0
  }, {
    "id" : 268,
    "fromDate" : "20180101",
    "toDate" : "20181231",
    "interestRate" : 1.2,
    "taxRate" : 0.0,
    "balance" : 1030000,
    "months" : 0,
    "days" : 365,
    "interest" : 12360.0,
    "tax" : 0.0
  }, {
    "id" : 269,
    "fromDate" : "20170101",
    "toDate" : "20171231",
    "interestRate" : 1.2,
    "taxRate" : 0.0,
    "balance" : 1000000,
    "months" : 0,
    "days" : 365,
    "interest" : 12000.0,
    "tax" : 0.0
  } ],
  "_links" : {
    "self" : {
      "href" : "/api/interest/13100260/20200101"
    },
    "interest-calculate" : {
      "href" : "/api/interest/13100260/20200101"
    },
    "receive" : {
      "href" : "/api/interest/13100260/20200101"
    },
    "interest-list" : {
      "href" : "/api/interest/13100260/log"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-interest-check"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-interest-calculate_response_body"><a class="link" href="#resources-interest-calculate_response_body">Response body</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code>{
  "accountNum" : "13100260",
  "lastIntsDt" : "20161231",
  "balance" : 1530000,
  "fromDate" : "20170101",
  "toDate" : "20191231",
  "basicRate" : 1.2,
  "interest" : 42720,
  "periodType" : "DAILY",
  "details" : [ {
    "id" : 267,
    "fromDate" : "20190101",
    "toDate" : "20191231",
    "interestRate" : 1.2,
    "taxRate" : 0.0,
    "balance" : 1530000,
    "months" : 0,
    "days" : 365,
    "interest" : 18360.0,
    "tax" : 0.0
  }, {
    "id" : 268,
    "fromDate" : "20180101",
    "toDate" : "20181231",
    "interestRate" : 1.2,
    "taxRate" : 0.0,
    "balance" : 1030000,
    "months" : 0,
    "days" : 365,
    "interest" : 12360.0,
    "tax" : 0.0
  }, {
    "id" : 269,
    "fromDate" : "20170101",
    "toDate" : "20171231",
    "interestRate" : 1.2,
    "taxRate" : 0.0,
    "balance" : 1000000,
    "months" : 0,
    "days" : 365,
    "interest" : 12000.0,
    "tax" : 0.0
  } ],
  "_links" : {
    "self" : {
      "href" : "/api/interest/13100260/20200101"
    },
    "interest-calculate" : {
      "href" : "/api/interest/13100260/20200101"
    },
    "receive" : {
      "href" : "/api/interest/13100260/20200101"
    },
    "interest-list" : {
      "href" : "/api/interest/13100260/log"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-interest-check"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-interest-calculate_response_fields"><a class="link" href="#resources-interest-calculate_response_fields">Response fields</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 33%;">
<col style="width: 33%;">
<col style="width: 33%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>accountNum</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">identification number of new account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>lastIntsDt</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">the last calculated date of account interest.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>balance</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">balance of account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>fromDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">start date of interest to calculate.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>toDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">end date of interest to calculate.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>basicRate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">interest rate of this account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>interest</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">result of interest.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>periodType</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">method of calculate about period, such as daily, monthly</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>details[0].id</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">identification number about interest detail information.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>details[0].fromDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">start date of interest to calculate.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>details[0].toDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">end date of interest to calculate.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>details[0].interestRate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">interest rate of this account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>details[0].taxRate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">tax rate of this account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>details[0].balance</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">balance of account at the time</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>details[0].months</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">calculate period in month</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>details[0].days</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">calculate period in day</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>details[0].interest</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">result of interest in this period.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>details[0].tax</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">result of tax in this period.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.self.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.interest-calculate.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to check how much the interest is.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.receive.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to withdraw interest from an existing account.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.interest-list.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to received interest list.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.profile.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self.</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-interest-calculate_response_headers"><a class="link" href="#resources-interest-calculate_response_headers">Response headers</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">HAL/JSON type content type</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-interest-calculate_links"><a class="link" href="#resources-interest-calculate_links">Links</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>self</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>interest-calculate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to check how much the interest is</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>receive</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to withdraw interest from an existing account</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>interest-list</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to received interest list</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>profile</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect2">
<h3 id="resources-interest-list"><a class="link" href="#resources-interest-list">이자지급 내역 조회</a></h3>
<div class="paragraph">
<p><code>GET</code> 요청을 사용해서 해당 계좌의 이자지급내역을 조회 할 수 있다.</p>
</div>
<div class="sect3">
<h4 id="resources-interest-list_curl_request"><a class="link" href="#resources-interest-list_curl_request">Example request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/api/interest/13100222/log?page=1&amp;size=5&amp;sort=id%2CASC' -i -X GET \
    -H 'Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da'</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-interest-list_http_request"><a class="link" href="#resources-interest-list_http_request">HTTP request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">GET /api/interest/13100222/log?page=1&amp;size=5&amp;sort=id%2CASC HTTP/1.1
Authorization: Bearer 5b847625-6add-44fc-a56f-a43f490867da
Host: localhost:8080</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-interest-list_http_response"><a class="link" href="#resources-interest-list_http_response">Example response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 200 OK
Content-Type: application/hal+json;charset=UTF-8
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Link: &lt;/docs/index.html&gt;; rel="profile"
Content-Length: 2739

{
  "_embedded" : {
    "dtoList" : [ {
      "id" : 242,
      "accountNum" : "13100222",
      "fromDate" : "20190701",
      "toDate" : "20201231",
      "basicRate" : 1.2,
      "interest" : 28338,
      "periodType" : "DAILY",
      "_links" : {
        "interest-detail" : {
          "href" : "/api/interest/13100222/log/242"
        }
      }
    }, {
      "id" : 245,
      "accountNum" : "13100222",
      "fromDate" : "20210101",
      "toDate" : "20200630",
      "basicRate" : 1.2,
      "interest" : 0,
      "periodType" : "DAILY",
      "_links" : {
        "interest-detail" : {
          "href" : "/api/interest/13100222/log/245"
        }
      }
    }, {
      "id" : 247,
      "accountNum" : "13100222",
      "fromDate" : "20200701",
      "toDate" : "20211231",
      "basicRate" : 1.2,
      "interest" : 28798,
      "periodType" : "DAILY",
      "_links" : {
        "interest-detail" : {
          "href" : "/api/interest/13100222/log/247"
        }
      }
    }, {
      "id" : 250,
      "accountNum" : "13100222",
      "fromDate" : "20220101",
      "toDate" : "20210630",
      "basicRate" : 1.2,
      "interest" : 0,
      "periodType" : "DAILY",
      "_links" : {
        "interest-detail" : {
          "href" : "/api/interest/13100222/log/250"
        }
      }
    }, {
      "id" : 252,
      "accountNum" : "13100222",
      "fromDate" : "20210701",
      "toDate" : "20221231",
      "basicRate" : 1.2,
      "interest" : 29318,
      "periodType" : "DAILY",
      "_links" : {
        "interest-detail" : {
          "href" : "/api/interest/13100222/log/252"
        }
      }
    } ]
  },
  "_links" : {
    "first" : {
      "href" : "http://localhost:8080/api/interest/13100222/log?page=0&amp;size=5&amp;sort=id,asc"
    },
    "prev" : {
      "href" : "http://localhost:8080/api/interest/13100222/log?page=0&amp;size=5&amp;sort=id,asc"
    },
    "self" : {
      "href" : "http://localhost:8080/api/interest/13100222/log?page=1&amp;size=5&amp;sort=id,asc"
    },
    "next" : {
      "href" : "http://localhost:8080/api/interest/13100222/log?page=2&amp;size=5&amp;sort=id,asc"
    },
    "last" : {
      "href" : "http://localhost:8080/api/interest/13100222/log?page=2&amp;size=5&amp;sort=id,asc"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-interest-list"
    },
    "interest-list" : {
      "href" : "/api/interest/13100222/log"
    },
    "interest-calculate" : {
      "href" : "/api/interest/13100222/20200104"
    },
    "interest-index" : {
      "href" : "/api/interest/13100222"
    }
  },
  "page" : {
    "size" : 5,
    "totalElements" : 12,
    "totalPages" : 3,
    "number" : 1
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-interest-list_response_body"><a class="link" href="#resources-interest-list_response_body">Response body</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code>{
  "_embedded" : {
    "dtoList" : [ {
      "id" : 242,
      "accountNum" : "13100222",
      "fromDate" : "20190701",
      "toDate" : "20201231",
      "basicRate" : 1.2,
      "interest" : 28338,
      "periodType" : "DAILY",
      "_links" : {
        "interest-detail" : {
          "href" : "/api/interest/13100222/log/242"
        }
      }
    }, {
      "id" : 245,
      "accountNum" : "13100222",
      "fromDate" : "20210101",
      "toDate" : "20200630",
      "basicRate" : 1.2,
      "interest" : 0,
      "periodType" : "DAILY",
      "_links" : {
        "interest-detail" : {
          "href" : "/api/interest/13100222/log/245"
        }
      }
    }, {
      "id" : 247,
      "accountNum" : "13100222",
      "fromDate" : "20200701",
      "toDate" : "20211231",
      "basicRate" : 1.2,
      "interest" : 28798,
      "periodType" : "DAILY",
      "_links" : {
        "interest-detail" : {
          "href" : "/api/interest/13100222/log/247"
        }
      }
    }, {
      "id" : 250,
      "accountNum" : "13100222",
      "fromDate" : "20220101",
      "toDate" : "20210630",
      "basicRate" : 1.2,
      "interest" : 0,
      "periodType" : "DAILY",
      "_links" : {
        "interest-detail" : {
          "href" : "/api/interest/13100222/log/250"
        }
      }
    }, {
      "id" : 252,
      "accountNum" : "13100222",
      "fromDate" : "20210701",
      "toDate" : "20221231",
      "basicRate" : 1.2,
      "interest" : 29318,
      "periodType" : "DAILY",
      "_links" : {
        "interest-detail" : {
          "href" : "/api/interest/13100222/log/252"
        }
      }
    } ]
  },
  "_links" : {
    "first" : {
      "href" : "http://localhost:8080/api/interest/13100222/log?page=0&amp;size=5&amp;sort=id,asc"
    },
    "prev" : {
      "href" : "http://localhost:8080/api/interest/13100222/log?page=0&amp;size=5&amp;sort=id,asc"
    },
    "self" : {
      "href" : "http://localhost:8080/api/interest/13100222/log?page=1&amp;size=5&amp;sort=id,asc"
    },
    "next" : {
      "href" : "http://localhost:8080/api/interest/13100222/log?page=2&amp;size=5&amp;sort=id,asc"
    },
    "last" : {
      "href" : "http://localhost:8080/api/interest/13100222/log?page=2&amp;size=5&amp;sort=id,asc"
    },
    "profile" : {
      "href" : "/docs/index.html#resources-interest-list"
    },
    "interest-list" : {
      "href" : "/api/interest/13100222/log"
    },
    "interest-calculate" : {
      "href" : "/api/interest/13100222/20200104"
    },
    "interest-index" : {
      "href" : "/api/interest/13100222"
    }
  },
  "page" : {
    "size" : 5,
    "totalElements" : 12,
    "totalPages" : 3,
    "number" : 1
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="resources-interest-list_response_fields"><a class="link" href="#resources-interest-list_response_fields">Response fields</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 33%;">
<col style="width: 33%;">
<col style="width: 33%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.first.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to first.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.prev.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to prev.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.next.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to next.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.last.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to last.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>page.size</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">size of one page.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>page.totalElements</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">amount of datas.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>page.totalPages</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">amount of pages.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>page.number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">current page number.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.dtoList[0].id</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">이자계산 상세 정보 기본키.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.dtoList[0].accountNum</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">해당 이자계산 결과의 계좌번호</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.dtoList[0].fromDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">이자계산 시작일자.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.dtoList[0].toDate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">이자계산 종료일자.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.dtoList[0].basicRate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">계좌의 기본 이율.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.dtoList[0].interest</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">계산 결과 이자.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.dtoList[0].periodType</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">이자계산의 기간 산정 방법. 일수, 월수, 일/월수</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_embedded.dtoList[0]._links.interest-detail.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">이자계산결과의 상세 정보 링크</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.self.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.interest-index.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">이자관련 첫 화면 링크 주소.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.interest-calculate.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to check how much the interest is.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.interest-list.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to received interest list.</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.profile.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self.</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-interest-list_response_headers"><a class="link" href="#resources-interest-list_response_headers">Response headers</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">HAL/JSON type content type</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="resources-interest-list_links"><a class="link" href="#resources-interest-list_links">Links</a></h4>
<table class="tableblock frame-all grid-all spread">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>first</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to first page</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>prev</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to prev page</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>self</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>next</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to next page</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>last</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to last page</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>interest-list</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">이자계산내역을 확인하는 링크 주소</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>interest-calculate</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">지정된 날짜까지 이자를 계산/결과를 확인하는 링크 주소</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>interest-index</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">이자관련 첫 화면 링크 주소</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>profile</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to profile.</p></td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
</div>
</div>
<div id="footer">
<div id="footer-text">
Last updated 2020-01-04 20:39:59 +09:00
</div>
</div>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/8.9.1/styles/github.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/8.9.1/highlight.min.js"></script>
<script>hljs.initHighlighting()</script>
</body>
</html>