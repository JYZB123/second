<template>
  <div>
    <div style="margin-top:50px;">
      <el-input v-model="teacherName" placeholder="请输入教师姓名" style="width:80%" clearable></el-input>
      <el-button type="primary" style="margin-left: 10px;" @click="searchTeacherByName">搜索</el-button>
      <el-button type="primary" style="margin-left:10px;margin-bottom: 20px;" @click="showDialog">新增教师</el-button>
    </div>
    <div>
      <el-table
          @selection-change="handleSelectionChange"
          :data="teachers"
          border>
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column
            prop="uid"
            label="序号"
            width="70">
        </el-table-column>
        <el-table-column
            prop="id"
            label="id"
            width="50">
        </el-table-column>
        <el-table-column
            prop="name"
            label="姓名"
            width="70">
        </el-table-column>
        <el-table-column
            prop="sex"
            label="性别"
            width="50">
        </el-table-column>
        <el-table-column
            prop="age"
            label="年龄"
            width="50">
        </el-table-column>
        <el-table-column
            prop="school"
            label="学校"
            width="120">
        </el-table-column>
        <el-table-column
            prop="major"
            label="院系/专业"
            width="150">
        </el-table-column>
        <el-table-column
            prop="birth"
            label="生日"
            width="150">
        </el-table-column>
        <el-table-column
            prop="salary"
            label="薪水"
            width="70">
        </el-table-column>
        <el-table-column
            prop="origin"
            label="籍贯"
            width="150">
        </el-table-column>
        <el-table-column
            prop="id_no"
            label="身份证号"
            width="150">
        </el-table-column>
        <el-table-column
            prop="phone"
            label="电话"
            width="150">
        </el-table-column>
        <el-table-column
            prop="email"
            label="邮箱"
            width="150">
        </el-table-column>
        <el-table-column
            prop="hobby"
            label="爱好"
            width="150">
        </el-table-column>
        <el-table-column
            label="操作">
          align="left">
          <template slot-scope="scope">
            <el-button
                size="mini"
                @click="handleEdit(scope.row)">编辑</el-button>
            <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="danger" size="mini" style="margin-top: 12px" :disabled="multipleSelection.length === 0" @click="deleteteachers">批量删除</el-button>
    </div>
    <el-dialog :title="dialogTitle[dialogStatus]" :visible.sync="dialogFormVisible" center>
      <el-form :model="form" :rules="rules" ref="teacher">
        <el-form-item label="id" :label-width="formLabelWidth" prop="id">
          <el-input v-model.number="form.id" :maxlength="3" placeholder="长度最大为3" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
          <el-input
              placeholder="长度大于2"
              v-model="form.name"
              autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择" style="width: 100%">
            <el-option
                v-for="item in options"
                :key="item.value"
                :value="item.label">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" :label-width="formLabelWidth" prop="age">
          <el-input
              placeholder="范围：18-60岁"
              v-model.number="form.age"
              autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学校" :label-width="formLabelWidth" prop="school">
          <el-input v-model="form.school" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="院系/专业" :label-width="formLabelWidth" prop="major">
          <el-cascader
              v-model="form.major"
              :options="options_major">
          </el-cascader>
        </el-form-item>
        <el-form-item label="生日" :label-width="formLabelWidth" prop="birth">
          <el-date-picker
              v-model="form.birth"
              type="date"
              placeholder="选择日期时间"
              format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="薪水" :label-width="formLabelWidth" prop="salary">
          <el-input v-model.number="form.salary" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="籍贯" :label-width="formLabelWidth" prop="origin">
          <el-input
              type="textarea"
              autosize
              v-model="form.origin"
              autocomplete="off">
          </el-input>
        </el-form-item>
        <el-form-item label="电话" :label-width="formLabelWidth" prop="phone">
          <el-input v-model.number="form.phone" placeholder="11位整数" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="身份证" :label-width="formLabelWidth" prop="id_no">
          <el-input v-model="form.id_no" placeholder="18位" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="爱好" :label-width="formLabelWidth" prop="hobby">
          <el-form-item>{{form.hobbies}}</el-form-item>
          <el-checkbox-group v-model="form.hobbies">
            <el-checkbox label="运动"></el-checkbox>
            <el-checkbox label="冒险"></el-checkbox>
            <el-checkbox label="娱乐"></el-checkbox>
            <el-checkbox label="智力"></el-checkbox>
            <el-checkbox label="收藏"></el-checkbox>
            <el-checkbox label="乐器"></el-checkbox>
            <el-checkbox label="文艺"></el-checkbox>
            <el-checkbox label="游戏"></el-checkbox>
          </el-checkbox-group>>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitTeacher">确 定</el-button>
      </div>
    </el-dialog>
    <el-upload
        style="margin-top:20px;"
        action="/upload"
        multiple
        :on-success="handleSuccess"
        :limit="3"
        :file-list="fileList">
      <el-button size="small" type="primary">点击上传</el-button>
    </el-upload>
  </div>
</template>

<script>
// @ is an alias to /src


export default {
  name: 'Home',
  components: {
  },
  data () {
    const validateAge = (rule, value, callback) => {
      if (value < 18) {
        callback(new Error('年龄太小！'))
      } else if (value > 60) {
        callback(new Error('年龄太大！'))
      } else {
        callback()
      }
    };
    return {
      fileList: [],
      teacherName: '',
      multipleSelection:[],
      dialogTitle: {
        addTeacher: "新增教师",
        editTeacher: "编辑教师"
      },
      dialogStatus: "",
      rules: {
        id: [
          { required: true, message: '请输入id' },
          { type: 'number', message: 'id必须为数字值'},
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2,  message: '长度必须大于2', trigger: 'blur' },
          {
            required: true,
            pattern: /^[\u4e00-\u9fa5]+$/,
            message: '姓名不支持特殊字符',
            trigger: 'blur'
          }
        ],
        sex: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        age: [
          { required: true, message: '年龄不能为空'},
          { type: 'number', message: '年龄必须为数字值'},
          { validator:validateAge, trigger:'blur'}
        ],
        school: [
          { required: true, message: '请输入学校', trigger: 'blur' }
        ],
        major: [
          { required: true, message: '请选择专业', trigger: 'blur' }
        ],
        birth: [
          { required: true, message: '请输入生日', trigger: 'blur'}
        ],
        salary: [
          { required: true, message: '请输入薪水' },
          { type: 'number', message: '薪水必须为数字值'}
        ],
        origin: [
          { required: true, message: '请输入籍贯', trigger: 'blur'},
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur'},
          {
            type:'number',
            required: true,

            message: '请输入正确的手机号码',
            trigger: 'blur'
          }
        ],
        id_no: [
          { required: true, message: '请输入身份证号', trigger: 'blur'},
          { min: 18, max: 18, message: '请如实填写18位号码', trigger: 'blur' },
          {
            required: true,
            pattern: /(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
            message: '请输入正确的身份证号码',
            trigger: 'blur'
          }
        ],
        email: [
          { required: true, message: '请输入身份证号', trigger: 'blur'},
          {
            required: true,
            pattern: /^([a-zA-Z0-9]+[-_.]?)+@[a-zA-Z0-9]+.[a-z]+$/,
            message: '请输入正确的电子邮箱',
            trigger: 'blur'
          }
        ]
      },
      options: [{
        value: '1',
        label: '男'
      }, {
        value: '2',
        label: '女'
      }
      ],
      options_major:[{
        value:'计算机与信息科学学院',
        label:'计算机与信息科学学院',
        children:[{
          value: '软件工程',
          label: '软件工程'
        }, {
          value: '计算机科学与技术',
          label: '计算机科学与技术'
        },{
          value: '电子商务',
          label: '电子商务'
        },{
          value: '智能科学与技术',
          label: '智能科学与技术'
        },{
          value: '其他',
          label: '其他'
        }]
      }
      ],
      msg: '',

      teachers:[],
      search: '',
      dialogFormVisible: false,
      form: {
        id:'',
        name: '',
        age: '',
        sex: '',
        school:'',
        major:'',

        birth:'',
        salary:'',
        origin:'',
        phone:'',
        id_no:'',
        email:'',
        hobby:'',
        hobbies:[]
      },
      formLabelWidth: '120px',
    }
  },
  created() {
    this.findAllTeachers();
  },
  watch:{
    teacherName () {
      this.findAllTeachers();
    }
  },
  methods: {
    // deleteFile (file) {
    //   console.log(file)
    // },
    handleSuccess (file) {
      const filename = file.name;
      this.$message({
        message: '文件上传成功！',
        type: 'success'
      });
    },
    searchTeacherByName () {
      if (this.teacherName !== '') {
        this.$axios.get("/teacher/findTeacherByName?name="+this.teacherName).then( (resp)=> {
          this.teachers = resp.data;
        }).catch( (error)=>{
          this.$message({
            type: 'error',
            message: "查询失败，原因是"+error.data.message
          });
        })
      }
    },
    deleteTeachers () {
      this.$confirm('此操作将永久删除教师, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let ids = '?';
        this.multipleSelection.forEach( (item)=>{
          ids += 'ids='+item.uid+'&'
        });
        this.$axios.post("/teacher/deleteTeachersByIds"+ids).then( (resp)=> {
          if (resp) {
            this.findAllTeachers();
            this.$message.success("删除成功！");
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val)
    },
    submitTeacher () {
      if (this.dialogStatus === 'addTeacher'){
        this.addTeacher();
      } else if(this.dialogStatus === "editTeacher") {
        this.editTeacher();
      }
    },
    findAllTeachers () {
      this.$axios.get("/teacher/findAllTeachers")
          .then((res) => {
            this.teachers = res.data
          })
          .catch(error => {
            this.$message({
              type: 'error',
              message: "查询失败，原因是"+error.data.message
            });
          });
    },
    handleEdit (row) {
      this.dialogFormVisible = true;
      this.dialogStatus = "editTeacher";
      this.form.uid = row.uid;
      this.form.id = row.id;
      this.form.name = row.name;
      this.form.age = row.age;
      this.form.sex = row.sex;
      this.form.school = row.school;
      this.form.major = row.major.split("/");
      this.form.birth = row.birth;
      this.form.salary = row.salary;
      this.form.origin = row.origin;
      this.form.phone = row.phone;
      this.form.id_no = row.id_no;
      this.form.email = row.email;
      this.form.hobby = row.hobby;
      this.form.hobbies = row.hobby.split(",");
    },
    handleDelete (row) {
      this.$confirm('此操作将永久删除该教师, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/teacher/deleteTeacher?uid="+row.uid).then ((resp)=>{
          this.$message.success("删除成功!")
          this.findAllTeachers();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    showDialog () {
      this.form.uid = '';
      this.form.id = '';
      this.form.name = '';
      this.form.age = '';
      this.form.sex = '';
      this.form.school = '';
      this.form.major = [];
      this.form.birth = '';
      this.form.salary = '';
      this.form.origin = '';
      this.form.phone = '';
      this.form.id_no = '';
      this.form.email = '';
      this.form.hobbies = [];
      this.dialogFormVisible = true;
      this.dialogStatus = "addTeacher";
    },
    addTeacher () {
      this.$refs.teacher.validate((valid) => {
        if (valid) {
          this.form.major=this.form.major.join("/");
          this.form.hobby=this.form.hobbies.join(",");
          this.$axios.post("/teacher/addTeacher",this.form).then( (resp)=>{
            if (resp) {
              this.dialogFormVisible = false;
              this.$message.success("添加成功！");
              this.findAllTeachers();
            }
          }).catch((error)=>{
            this.$message({
              message: '添加数据失败，原因是'+error.data.message,
              type: 'error'
            })
          })
        } else {
          this.$message({
            message: '请输入所有字段',
            type: 'error'
          })
          return false;
        }
      });
    },
    editTeacher () {
      debugger
      this.form.major=this.form.major.join("/");
      this.form.hobby=this.form.hobbies.join(",");
      this.$axios.post("/teacher/updateTeacher",this.form).then( (resp)=>{
        if(!resp.data.code) {
          this.dialogFormVisible = false;
          this.$message.success("修改成功!");
          this.findAllTeachers();
        }
      }).catch( (error)=> {
        this.$message({
          message: '数据更新失败，原因是'+error.data.message,
          type: 'error'
        })
      })
    }
  }
}
</script>
<style scoped>
.table {
  margin: 0 auto;
  clear: both;
}
</style>
