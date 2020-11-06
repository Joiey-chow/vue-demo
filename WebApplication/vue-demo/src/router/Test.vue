<template>
  <div>
    <el-table style="width: 100%" :data="tableData.filter(data=>!search||data.name.toLowerCase().includes(search.toLowerCase()))">

      <el-table-column prop="id" label="ID" width="200px"></el-table-column>
      <el-table-column prop="name" label="姓名" width="400px"></el-table-column>
      <el-table-column prop="pwd" label="密码" width="400px"></el-table-column>
      <el-table-column  label="操作" width="200px">
        <template slot-scope="scope">                                       <!--插槽获取行的数据-->
        <el-button type="danger" @click="test123(scope.row)" >删除</el-button>
        <el-button type="info" @click="Updata(scope.row)" >修改</el-button>
        </template>
      </el-table-column>
      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <el-input v-model="search" placeholder="输入关键字搜索名字" size="mini"></el-input>
        </template>
      </el-table-column>

    </el-table>
    <el-button style="width: 100%" @click="dialogFormVisible=true" >插入用户数据</el-button><br>
    <el-button style="width: 100%" @click="GetData" :loading=loadingstatus>查询所有用户信息</el-button>

    <!--弹窗-->
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" :before-close="Willclose">
      <el-form :model="ruleForm" :rules="rule" ref="ruleForm">
        <el-form-item label="用户名" prop="name">
          <el-input style="width: 100%" v-model="ruleForm.name" clearable ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pwd">
          <el-input style="width: 100%" v-model="ruleForm.pwd" clearable ></el-input>
        </el-form-item>
        <el-button @click="submitForm('ruleForm')" style="width: 100%" >上传</el-button>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: 'Test',
    data(){
      return{
        loadingstatus:false,
        ruleForm:{
          id:'',
          name:'',
          pwd:'',
        },
        search:'',
        dialogFormVisible:false,
        tableData:[],
        choose:'0',
        rule:{
          name:[
            {
              required:true,
              message:'不能为空',
              trigger:'blur'
            },
            ],
          pwd:[
            {
              required: true,
              message:'不能为空',
              trigger: 'blur'
          }
            ]
        }
    }
    },
    methods:{
      submitForm(FromName){
        this.$refs[FromName].validate((valid)=>{
          if (valid){
            this.InsertData();
          }else {
            this.$notify.error({
              message:'参数不能为空',
            })
          }
        })
      },
      GetData(){
        this.loadingstatus=true;
        const url='/api/findAll';
        this.$axios.get(url).then(response=>{
          const result=response["data"]["results"];
          const tableData=[];
          for(let i in result){
            tableData.push(result[i]);
          }
          let that = this;
          that.tableData=tableData;
          this.loadingstatus=false;
        })
      },
      InsertData(){
        this.dialogFormVisible=false;
        if(this.choose===1){
          const url='/api/update';
          this.$axios.post(url,{
            id:this.ruleForm.id,
            name:this.ruleForm.name,
            pwd:this.ruleForm.pwd
          }).then(response=>{
            if(response.data.code===200){
              this.$notify({
                message:'修改成功',
                type:'success'
              })
            }
            else {
              this.$notify.error({
                message:'修改失败',
              })
            }
          })
        }
        else{const url='/api/insert';
        this.$axios.post(url,{
          name:this.ruleForm.name,
          pwd:this.ruleForm.pwd
        }).then(response=>{
          if (response.data.code===200){
            this.$notify({
              message:'上传成功',
              type:'success'
            })
          }else {
            this.$notify.error({
              message:'上传失败'
            })
          }
        })}
        this.ruleForm.id=null;                                 //上传数据后窗口关闭数据清空
        this.ruleForm.name=null;
        this.ruleForm.pwd=null;
        this.GetData();
      },
      test123(rowData){
        const cid=rowData.id;
        const url='/api/del';
        this.$axios.post(url,{
          id:cid
        }).then(response=>{
          if(response.data.code===200){
            this.$notify({
              message:'删除成功'  ,
              type:'success'
            })
            this.GetData();
          }else {
            this.$notify.error({
              message:'删除失败'
            })
          }

        })
      },
      Updata(info){
        this.dialogFormVisible=true;
        this.ruleForm.id=info.id;
        this.ruleForm.name=info.name;
        this.ruleForm.pwd=info.pwd;
        this.choose=1;                            //弹窗执行更新数据
      },
      Willclose(){                                //关闭弹窗数据清零
        this.ruleForm.id=null;
        this.ruleForm.name=null;
        this.ruleForm.pwd=null;
        this.dialogFormVisible=false;
      }
    }
  }
</script>

<style scoped>

</style>
