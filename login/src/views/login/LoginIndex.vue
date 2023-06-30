<template>
<el-container>
    <el-header height=100px>
        <h1>在线口语考试平台</h1>
        <!-- 考试界面名称 -->
    </el-header>
    <el-main>
    <el-form ref="form" :model="form" label-width="80px">
        <!-- 建立登录表单用于登陆验证 -->
  <el-form-item label="用户名">
    <el-input v-model="form.username" maxlength=20 placeholder="请输入用户名" style="width:250px" prop="username"></el-input>
  </el-form-item>
  <el-form-item label="密码">
    <el-input v-model="form.password" maxlength=20 placeholder="请输入密码" show-password style="width:250px" prop="password"></el-input>
  </el-form-item>

  
  
  <el-form-item label="身份">
    <el-select v-model="form.type" placeholder="请选择登录身份" prop='type'>
      <el-option label="管理员" value="1"></el-option>
      <el-option label="学生" value="2"></el-option>
    </el-select>
  </el-form-item>
  
  <el-form-item>
    <el-button type="primary" @click="onSubmit" @keyup.enter.native="onSubmit">登录</el-button>
    <!-- 登录按钮链接提交函数，利用axios异步向服务器发送check请求判断登录 -->
     <el-button @click="reset('form')">重置</el-button>
     <!-- 重置按钮 -->
  </el-form-item>
</el-form>

<el-button type="text" @click="dialogFormVisible = true">马上去注册</el-button>
<!-- 创建一个嵌入表单的dialogue窗口，用于新账号的注册 -->
<el-dialog title="注册新账号" :visible.sync="dialogFormVisible"> <!--设置visible参数用于自动关闭窗口 -->

    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <!-- 创建一个带有内容核验规则的表单，用于用户名称的注册 -->
        <el-form-item label="用户名" prop="username">
        <el-input v-model="ruleForm.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
            <el-form-item label="密码" prop="pass">
            <el-input  v-model="ruleForm.pass" autocomplete="off" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
            <el-input  v-model="ruleForm.checkPass" autocomplete="off" placeholder="请再次输入密码" show-password></el-input>
        </el-form-item>
        
        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">注 册</el-button>
            <el-button @click="resetForm('ruleForm')">重 置</el-button>
        </el-form-item>
    </el-form>
  
</el-dialog>

</el-main>
</el-container>
</template>


<script>
import {login} from '@/api/user'
import {setToken} from '@/utils/auth'


export default {
    data(){
        //  定义校验规则
         var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass');
                }
            callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm.pass) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        }
        

        
        return{
            ruleForm: {
                pass: '',
                checkPass: '',
                age: ''
            },
            form:{
                username:"",
                password:"",
                type:""
            },
            rules: {
                // 定义规则触发方式，失焦则触发校验
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validatePass2, trigger: 'blur' }
                ]   
            },
             dialogTableVisible: false,
             dialogFormVisible: false,
             formLabelWidth: '120px',
            
         
        };
    
    },
    methods:{
         submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    alert('注册成功!请牢记您的账号和密码！');
                } else {
                    console.log('注册失败，请再次尝试!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        onSubmit:function(){
                    login(this.from).then((result) => {
                        console.log(result)
                        if (result.data.code == 1) {
                            setToken(result.data.data);
                            console.log('login success');
                            this.$router.push('/');//跳转主页
                        } else {
                            this.$message.error(result.data.msg);
                        }
                    });
               
                
            },
        
        reset(form){
            this.$refs[form].resetFields();
            
        }
        

    }
    
}
</script>

<style>

.frame-create{
    width:500px
}
</style>