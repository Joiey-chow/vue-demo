<template>
  <div class="background">
    <div id="app" style="justify-content: center;display: flex;margin-top: 150px;border-radius: 5px;">
      <!--    <div v-if="!repoUrl">loading</div>-->
      <!--    <div v-else>most star repo is <a :href="repoUrl">{{repoName}}</a></div>-->
      <el-card style="width: 400px">
        <div style="font-size: 30px">登录</div>
        <!--    <div v-if="!repoName">loading...</div>-->
        <!--    <div v-else >{{repoName}}</div>-->
        <h1 style="border: 5px">
          <el-input v-model="username" placeholder="请输入用户"></el-input>
          <el-input v-model="pwd" placeholder="请输入密码" show-password></el-input>
        </h1>
<!--        <el-button type="primary" @click="login">测试</el-button>-->
        <el-button type="primary" @click="PostD">发送数据</el-button>
      </el-card>
    </div>
  </div>
</template>

<script>
  import {mapMutations} from 'vuex';
  export default {
    name: 'Login',
    data(){
      return{
          username:'',
          pwd:'',
          repoUrl:'',
          repoName:''
        }
    },
    methods:{
      ...mapMutations(['changeLogin']),
    //   login(){
    //     const url='/api/test'
    //     this.$axios.get(url).then(response=>{
    //       const result=JSON.stringify(response);
    //       this.repoName=(JSON.parse(result))['data']['Name'];
    //       console.log(this.repoName);
    //       // const result= response
    //       // this.repoUrl=result.msg
    //       // this.repoName=result.status
    //     }).catch(error=>{
    //       alert("请求失败")
    //     })
    // },
      PostD(){
       // const url='/api/login';
        const url ='/api/login';
        this.$axios.post(url,{
          name:this.username,
          pwd:this.pwd
        }).then(response=>{
         // console.log(response.data.code);
          if (response.data.code === '200'){
            this.userToken=response.data.token;
            this.changeLogin({Authorization:this.userToken});
            this.repoName='数据校验成功';
            this.$router.push({name:'Test'})
          //  console.log(response);
          }
          else {
            this.repoName='数据不匹配';
            alert(this.repoName);
          //  console.log(response);
          }
        })
      }
    }
  }

</script>

<style scoped>
  .background {
    /*图片满拉伸*/
    position: fixed;
    background-image: url("/static/T1.jpg");
    background-repeat: no-repeat;
    background-size: 100% 100%;
    width: 100%;
    height: 100%;
  }
</style>
