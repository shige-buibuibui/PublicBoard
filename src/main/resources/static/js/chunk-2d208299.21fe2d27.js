(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d208299"],{a42b:function(e,t,a){"use strict";a.r(t);a("ac1f"),a("841c");var n=a("7a23"),i=Object(n["p"])("查看"),l=Object(n["p"])("删除"),c={class:"block",style:{"text-align":"center",margin:"10px"}};function o(e,t,a,o,r,p){var u=Object(n["S"])("el-table-column"),s=Object(n["S"])("el-input"),b=Object(n["S"])("el-button"),d=Object(n["S"])("el-table"),g=Object(n["S"])("el-pagination");return Object(n["J"])(),Object(n["m"])("div",null,[Object(n["q"])(d,{data:r.table.data,style:{width:"100%","min-width":"1000px"},stripe:""},{default:Object(n["hb"])((function(){return[(Object(n["J"])(!0),Object(n["m"])(n["b"],null,Object(n["Q"])(r.table.tableColumn,(function(e,t){return Object(n["J"])(),Object(n["k"])(u,{prop:e.prop,label:e.label,"min-width":e.minWidth,key:t,align:"center","show-overflow-tooltip":""},null,8,["prop","label","min-width"])})),128)),Object(n["q"])(u,{fixed:"right",label:"操作",align:"left",width:"250px"},{header:Object(n["hb"])((function(){return[Object(n["q"])(s,{modelValue:r.search,"onUpdate:modelValue":t[0]||(t[0]=function(e){return r.search=e}),size:"mini",placeholder:"Type to search"},null,8,["modelValue"])]})),default:Object(n["hb"])((function(){return[Object(n["q"])(b,{size:"mini"},{default:Object(n["hb"])((function(){return[i]})),_:1}),Object(n["q"])(b,{size:"mini",type:"danger"},{default:Object(n["hb"])((function(){return[l]})),_:1})]})),_:1})]})),_:1},8,["data"]),Object(n["n"])("div",c,[Object(n["q"])(g,{"current-page":r.currentpage,"onUpdate:current-page":t[1]||(t[1]=function(e){return r.currentpage=e}),"page-sizes":r.pagesizes,"onUpdate:page-sizes":t[2]||(t[2]=function(e){return r.pagesizes=e}),"page-size":r.pagesize,"onUpdate:page-size":t[3]||(t[3]=function(e){return r.pagesize=e}),total:r.total,"onUpdate:total":t[4]||(t[4]=function(e){return r.total=e}),layout:"total, sizes, prev, pager, next, jumper",background:""},null,8,["current-page","page-sizes","page-size","total"])])])}var r={name:"Poststable",data:function(){return{search:"",pagesizes:[10,20,50,100],currentpage:1,pagesize:10,total:1,table:p}},methods:{gettable:function(){var e=this;this.$axios.get("/news/admin/checkNews?page="+this.currentpage+"&size="+this.pagesize).then((function(t){e.table.data=t.data.data.list,e.total=t.data.data.total,console.log("请求帖子表成功"),console.log(t)})).catch((function(){console.log("请求帖子表失败")}))}},created:function(){this.gettable()},watch:{currentpage:function(){this.gettable()},pagesize:function(){this.gettable()}}},p={tableColumn:[{prop:"newstitle",label:"帖子标题",minWidth:"150px"},{prop:"newstypename",label:"类型",minWidth:"50px"},{prop:"userid",label:"作者账号",minWidth:"120px"},{prop:"sendtime",label:"发帖时间"},{prop:"viewnum",label:"浏览量",minWidth:"60px"},{prop:"likenum",label:"点赞量",minWidth:"60px"}],data:[]},u=a("6b0d"),s=a.n(u);const b=s()(r,[["render",o]]);t["default"]=b}}]);
//# sourceMappingURL=chunk-2d208299.21fe2d27.js.map