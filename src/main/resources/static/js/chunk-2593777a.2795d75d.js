(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2593777a"],{2167:function(t,e,a){"use strict";a("b478")},3304:function(t,e,a){},"4ef5":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticStyle:{position:"relative"}},[a("div",{staticClass:"banner"}),a("div",{staticClass:"infoForm"},[a("el-upload",{attrs:{action:"123",name:"avatar","http-request":t.myupload,"show-file-list":!1}},[a("el-avatar",{staticStyle:{border:"5px solid white"},attrs:{size:150,src:t.currentUser.headimage,fit:"fill",shape:"square"}})],1),a("el-form",{attrs:{"label-width":"auto"},model:{value:t.currentUser,callback:function(e){t.currentUser=e},expression:"currentUser"}},t._l(t.personalInof,(function(e,n){return a("el-form-item",{attrs:{prop:e.prop,label:e.label}},[[a("div",{directives:[{name:"show",rawName:"v-show",value:e.edit,expression:"item.edit"}]},[a("el-input",{staticStyle:{border:"none",width:"250px","margin-right":"10px"},model:{value:e.data,callback:function(a){t.$set(e,"data",a)},expression:"item.data"}}),a("el-button",{on:{click:function(e){return t.updateprofile(n)}}},[t._v("保存")]),a("el-button",{on:{click:function(t){e.edit=!e.edit}}},[t._v("取消")])],1),a("div",{directives:[{name:"show",rawName:"v-show",value:!e.edit,expression:"!item.edit"}]},[t._v(" "+t._s(t.currentUser[e.prop])+" "),e.disabled?a("a",{staticClass:"el-icon-edit",staticStyle:{"margin-left":"10px",cursor:"pointer"},on:{click:function(a){e.edit=!e.edit,e.data=t.currentUser[e.prop]}}}):t._e()])]],2)})),1),a("el-button",{attrs:{type:"danger"},on:{click:t.logout}},[t._v("退出登录")])],1)])},i=[],r=a("5530"),o=a("2f62"),s=a("90d2"),l=a("6c33"),c=(a("a18c"),a("84af")),d={name:"Setting",components:{Banner:c["a"]},data:function(){return{personalInof:[{prop:"userid",label:"用户id:",disabled:!1,edit:!1,data:""},{prop:"username",label:"用户名:",disabled:!0,edit:!1,data:""},{prop:"email",label:"邮箱:",disabled:!0,edit:!1,data:""},{prop:"usertype",label:"用户类型:",disabled:!1,edit:!1,data:""},{prop:"banstate",label:"状态:",disabled:!1,edit:!1,data:""}]}},methods:{logout:function(){var t=this;axios.get("user/logout").then((function(){t.$store.commit(s["a"]),t.$router.push({path:"/"})}))},myupload:function(t){var e=this;console.log(t);var a=new FormData;a.append("avatar",t.file),axios.post("user/updateprofile",a).then((function(t){console.log(t),e.$store.dispatch(l["b"])}))},updateprofile:function(t){var e=this;if(this.personalInof[t].data!=this.currentUser[this.personalInof[t].prop]){var a=new FormData;a.append(this.personalInof[t].prop,this.personalInof[t].data),axios.post("user/updateprofile",a).then((function(t){console.log(t),e.$store.dispatch(l["b"])}))}this.personalInof[t].edit=!this.personalInof[t].edit}},computed:Object(r["a"])({},Object(o["b"])(["currentUser"]))},u=d,p=(a("2167"),a("2877")),f=Object(p["a"])(u,n,i,!1,null,"e7566e4c",null);e["default"]=f.exports},8489:function(t,e,a){"use strict";a("3304")},"84af":function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement;t._self._c;return t._m(0)},i=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"banner"},[a("h1",{staticClass:"logo-font",attrs:{"data-spotlight":"PublicBoard!"}},[t._v("PublicBoard")])])}],r={name:"Banner"},o=r,s=(a("8489"),a("2877")),l=Object(s["a"])(o,n,i,!1,null,"36176ede",null);e["a"]=l.exports},b478:function(t,e,a){}}]);
//# sourceMappingURL=chunk-2593777a.2795d75d.js.map