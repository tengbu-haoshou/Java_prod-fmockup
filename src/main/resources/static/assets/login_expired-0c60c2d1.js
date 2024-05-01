import{r as a,a as i,o as b,c as h,b as e,w as t,F as Q,d as N,e as m,t as c,f as B,_ as R,l as H,g as X,h as Y,i as Z,j as ee,k as oe,m as te}from"./materialdesignicons-85a25ac4.js";import{_ as ae}from"./header_for_login-915019d6.js";import{_ as le}from"./token-7f0f6049.js";const ne={key:0},se={key:1},ue={style:{color:"red"}},re={key:2},de={class:"display-1"},ie={style:{color:"red"}},ce={style:{"font-size":"small",color:"red"}},ve={style:{"font-size":"small",color:"red"}},_e={style:{"font-size":"small",color:"red"}},fe={__name:"login_expired",setup(pe){const u=a(""),p=a({currentPassword:"",newPassword:"",confirmPassword:""}),_=a(0),V=a(0),D=window.innerWidth;D<600?(_.value=12,V.value=12):D<1024?(_.value=6,V.value=10):(_.value=4,V.value=8);const f=a(!0),r=a(""),W=a(!0),F=a(""),z=a(""),T=a(""),U=a(""),j=a(""),S=a("");function q(n,o,v){if(n=="action-ok"){const l=v;F.value=l.login_expired_body_title,z.value=l.login_expired_form_currentPassword,T.value=l.login_expired_form_newPassword,U.value=l.login_expired_form_confirmPassword,j.value=l.login_expired_form_save,S.value=l.login_expired_form_cancel}else r.value="error",u.value=o}function G(n,o){n!="action-ok"&&(r.value="error",u.value=o)}const A=a("");function I(n,o,v){n=="action-ok"?A.value=v:(r.value="error",u.value=o)}const w=a(!1),g=a(!1),y=a(!1),x=a(""),k=a(""),P=a("");f.value=!1,r.value==""&&(r.value="normal");async function J(){f.value=!0;const n=new FormData;n.append("token",A.value),n.append("currentPassword",x.value!=null?x.value:""),n.append("newPassword",k.value!=null?k.value:""),n.append("confirmPassword",P.value!=null?P.value:""),await H.post("/session/enabled",n).then(function(o){if(o.data.status=="action-ok"){window.location.href="/pages/home.html";return}else u.value=o.data.message,p.value=o.data.messageMap}).catch(function(o){u.value="Network trouble has occurred."}),f.value=!1}function K(){f.value=!0;const n=new FormData;H.post("/session/unauth",n).then(function(o){window.location.href="/pages/login.html"}).catch(function(o){u.value="Network trouble has occurred."}),f.value=!1}return(n,o)=>{const v=i("v-main"),l=i("v-col"),d=i("v-row"),C=i("v-container"),$=i("v-text-field"),E=i("v-btn"),L=i("v-form"),O=i("v-app");return b(),h(Q,null,[e(O,null,{default:t(()=>[e(ae,{logoutButton:W.value,onHeader_event:G},null,8,["logoutButton"]),r.value==""?(b(),h("div",ne,[e(v)])):N("",!0),r.value=="error"?(b(),h("div",se,[e(v,null,{default:t(()=>[e(C,{fluid:""},{default:t(()=>[e(d,{"no-gutters":""},{default:t(()=>[e(l,null,{default:t(()=>[m("div",ue,c(u.value),1)]),_:1})]),_:1})]),_:1})]),_:1})])):N("",!0),r.value=="normal"?(b(),h("div",re,[e(v,null,{default:t(()=>[e(C,{fluid:""},{default:t(()=>[e(d,{"no-gutters":""},{default:t(()=>[e(l,null,{default:t(()=>[m("h3",de,c(F.value),1)]),_:1})]),_:1}),e(d,{"no-gutters":""},{default:t(()=>[e(l,null,{default:t(()=>[m("div",ie,c(u.value),1)]),_:1})]),_:1}),e(d,{"no-gutters":""},{default:t(()=>[e(l,null,{default:t(()=>[e(L,null,{default:t(()=>[e(C,{fluid:""},{default:t(()=>[e(d,{"no-gutters":""},{default:t(()=>[e(l,{cols:_.value},{default:t(()=>[m("div",ce,c(p.value.currentPassword),1),e($,{autocomplete:"off","prepend-icon":"mdi-lock",clearable:"",label:z.value,modelValue:x.value,"onUpdate:modelValue":o[0]||(o[0]=s=>x.value=s),type:w.value?"text":"password","append-icon":w.value?"mdi-eye":"mdi-eye-off","onClick:append":o[1]||(o[1]=s=>w.value=!w.value)},null,8,["label","modelValue","type","append-icon"])]),_:1},8,["cols"])]),_:1}),e(d,{"no-gutters":"",align:"center"},{default:t(()=>[e(l,{cols:_.value},{default:t(()=>[m("div",ve,c(p.value.newPassword),1),e($,{autocomplete:"off","prepend-icon":"mdi-lock",clearable:"",label:T.value,modelValue:k.value,"onUpdate:modelValue":o[2]||(o[2]=s=>k.value=s),type:g.value?"text":"password","append-icon":g.value?"mdi-eye":"mdi-eye-off","onClick:append":o[3]||(o[3]=s=>g.value=!g.value)},null,8,["label","modelValue","type","append-icon"])]),_:1},8,["cols"])]),_:1}),e(d,{"no-gutters":""},{default:t(()=>[e(l,{cols:_.value},{default:t(()=>[m("div",_e,c(p.value.confirmPassword),1),e($,{autocomplete:"off","prepend-icon":"mdi-lock",clearable:"",label:U.value,modelValue:P.value,"onUpdate:modelValue":o[4]||(o[4]=s=>P.value=s),type:y.value?"text":"password","append-icon":y.value?"mdi-eye":"mdi-eye-off","onClick:append":o[5]||(o[5]=s=>y.value=!y.value)},null,8,["label","modelValue","type","append-icon"])]),_:1},8,["cols"])]),_:1}),e(d,{"no-gutters":""},{default:t(()=>[e(l,{cols:"auto"},{default:t(()=>[e(E,{variant:"outlined",color:"success",onClick:o[6]||(o[6]=s=>J()),loading:f.value,style:{"text-transform":"none"}},{default:t(()=>[B(c(j.value),1)]),_:1},8,["loading"]),B("  "),e(E,{variant:"outlined",color:"primary",onClick:o[7]||(o[7]=s=>K()),style:{"text-transform":"none"}},{default:t(()=>[B(c(S.value),1)]),_:1})]),_:1})]),_:1})]),_:1})]),_:1})]),_:1})]),_:1})]),_:1})]),_:1})])):N("",!0)]),_:1}),e(R,{onTexts_event:q}),e(le,{onToken_event:I})],64)}}},me=X({components:Z,directives:ee,icons:{defaultSet:"mdi",aliases:oe,sets:{mdi:te}}}),M=Y(fe);M.use(me);M.mount("#app");
