(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d21a407"],{bb5f:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-table",{staticStyle:{width:"100%","min-width":"1000px"},attrs:{data:e.table.data,stripe:""}},[e._l(e.table.tableColumn,(function(e){return a("el-table-column",{attrs:{prop:e.prop,label:e.label,"min-width":e.minWidth,align:"center","show-overflow-tooltip":"",sortable:""}})})),a("el-table-column",{attrs:{fixed:"right",label:"操作",align:"left",width:"250px"},scopedSlots:e._u([{key:"header",fn:function(){return[a("el-input",{attrs:{size:"mini",placeholder:"Type to search"},model:{value:e.search,callback:function(t){e.search=t},expression:"search"}})]},proxy:!0}])},[[a("el-button",{attrs:{size:"mini"}},[e._v("查看")]),a("el-button",{attrs:{size:"mini",type:"danger"}},[e._v("删除")])]],2)],2),a("div",{staticClass:"block",staticStyle:{"text-align":"center",margin:"10px"}},[a("el-pagination",{attrs:{"current-page":e.currentpage,"page-sizes":e.pagesizes,"page-size":e.pagesize,total:e.total,layout:"total, sizes, prev, pager, next, jumper",background:""},on:{"update:currentPage":function(t){e.currentpage=t},"update:current-page":function(t){e.currentpage=t},"update:pageSizes":function(t){e.pagesizes=t},"update:page-sizes":function(t){e.pagesizes=t},"update:pageSize":function(t){e.pagesize=t},"update:page-size":function(t){e.pagesize=t},"update:total":function(t){e.total=t}}})],1)],1)},i=[],l={name:"Usertable",data:function(){return{search:"",pagesizes:[10,20,50,100],currentpage:1,pagesize:10,total:1,table:s}},methods:{gettable:function(){var e=this;this.$axios.get("/subject/admin/checksubject?page="+this.pagesize+"&page="+this.currentpage).then((function(t){e.table.data=t.data.data.list,e.total=t.data.data.total,console.log("请求话题表成功"),console.log(t)})).catch((function(){console.log("请求话题表失败")}))}},created:function(){this.gettable()},watch:{currentpage:function(){this.gettable()},pagesize:function(){this.gettable()}}},s={tableColumn:[{prop:"subjecttitle",label:"话题标题",minWidth:"150px"},{prop:"subjecttypename",label:"类型",minWidth:"50px"},{prop:"userid",label:"作者账号",minWidth:"120px"},{prop:"sendtime",label:"发帖时间"},{prop:"viewnum",label:"浏览量",minWidth:"60px"},{prop:"likenum",label:"点赞量",minWidth:"60px"}],data:[]},p=l,o=a("2877"),r=Object(o["a"])(p,n,i,!1,null,"ad79e7ce",null);t["default"]=r.exports}}]);
//# sourceMappingURL=chunk-2d21a407.a0439531.js.map