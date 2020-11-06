<template>
  <router-view></router-view>
</template>

<script>
export default {
  data(){

    return{
      username:'',
      pwd:'',
      repoUrl:'',
      repoName:''
    }
  },
  mounted () {
    //const url='https://api.github.com/search/repositories?q=v&sort=stars'
    //const url='http://localhost:8080/test'
    /*this.$http.get(url).then(
      response=>{
        const result= response.data
        const mostRepo =result.items[0]
        this.repoUrl=mostRepo.html_url
        this.repoName=mostRepo.name
      },
      response=>{
        alert("请求失败")
      }
    )*/
  },
  methods:{

    },

  }



</script>

<style>

</style>
