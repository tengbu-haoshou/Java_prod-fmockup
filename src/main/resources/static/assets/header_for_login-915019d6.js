import{r as t,a,o as d,c as N,b as r,w as s,e as D,t as l,n as F,f as h,d as V,_ as T,F as $,l as j}from"./materialdesignicons-85a25ac4.js";const E={class:"display-1"},_=2023,A={__name:"header_for_login",props:{logoutButton:Boolean},emits:["header_event"],setup(w,{emit:g}){const x=t(w.logoutButton),i=t(!1);window.innerWidth<1024&&(i.value=!0),i.value=!0;const p=t(""),m=t(""),v=t(""),c=t(""),f=new Date().getFullYear();f>_?c.value=_+"-"+f:c.value=_;const b=g;function k(n,e,u){if(n=="action-ok"){const o=u;p.value=o.common_header_title,m.value=o.common_footer_company,v.value=o.common_header_form_logout}else b("header_event",e)}function y(){const n=new FormData;j.post("/session/unauth",n).then(function(e){window.location.href="/pages/login.html"}).catch(function(e){alert("Network trouble has occurred.")})}return(n,e)=>{const u=a("v-app-bar-title"),o=a("v-btn"),B=a("v-app-bar"),C=a("v-footer");return d(),N($,null,[r(B,{color:"blue",app:"",dark:""},{default:s(()=>[r(u,null,{default:s(()=>[D("h3",E,l(p.value),1)]),_:1}),x.value?(d(),F(o,{key:0,variant:"outlined",color:"white",onClick:e[0]||(e[0]=q=>y()),style:{"text-transform":"none"}},{default:s(()=>[h(l(v.value),1)]),_:1})):V("",!0)]),_:1}),r(C,{color:"blue",app:"",dark:""},{default:s(()=>[h("Copyright © "+l(c.value)+" "+l(m.value),1)]),_:1}),r(T,{onTexts_event:k})],64)}}};export{A as _};
