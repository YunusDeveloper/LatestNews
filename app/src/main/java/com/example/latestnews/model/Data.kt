package com.example.latestnews.model

data class News(
    var status:String?,
    var totalResult:Int?,
    var articles:List<Article>
)

data class Article(var sourse:List<Source>?,
                          var author:String?,
                          var title:String?,
                          var description:String?,
                          var url:String?,
                          var urlToImage:String?,
                          var publishedAt:String?,
                          var content:String?)

data class Source(var id:String?,
                         var name: String?)