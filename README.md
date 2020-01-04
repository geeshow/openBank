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