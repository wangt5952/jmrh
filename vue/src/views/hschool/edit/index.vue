<template>
<div class="tab-container">

    <div class="" style="height: 80% !important;overflow-y: auto;">
      <el-tabs type="border-card">
        <el-tab-pane>
          <span slot="label"><i class="el-icon-date"></i> 高校基本信息</span>
          <el-form class="" label-width="30%" style="text-align:left">
            <el-row :gutter="20">
              <el-col :span="20">

                <el-form-item label="名称">
                  <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                  <el-input placeholder="请输入名称" v-model="school.name" style="width:80%"></el-input>
                </el-form-item>
                <el-form-item label="统一社会信用代码">
                  <span style='position: relative;left: -140px;color: #f60d0d;'>*</span>
                  <el-input placeholder="请输入统一社会信用代码" v-model="school.code" style="width:80%"></el-input>
                </el-form-item>
                <el-form-item label="事业单位法人证书正本">
                  <span style='position: absolute;left: -160px;color: #f60d0d;'>*</span>
                  <div>
                    <!--这是背面照-->
                    <div class="photo photo1">
                      <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="school.zhengben" list-type="picture">
                        <el-button size="small" type="primary">点击上传</el-button>
                      </el-upload>

                    </div>
                  </div>
                </el-form-item>
                <el-form-item label="事业单位法人证书副本">
                  <span style='position: absolute;left: -160px;color: #f60d0d;'>*</span>
                  <div>
                    <!--这是背面照-->
                    <div class="photo photo1">
                      <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="school.fuben" list-type="picture">
                        <el-button size="small" type="primary">点击上传</el-button>
                      </el-upload>

                    </div>
                  </div>
                </el-form-item>
                <el-form-item label="高校logo">
                  <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                  <div>
                    <!--这是背面照-->
                    <div class="photo photo1">
                      <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="school.logo" list-type="picture">
                        <el-button size="small" type="primary">点击上传</el-button>
                      </el-upload>

                    </div>
                  </div>
                </el-form-item>
                <el-form-item label="所在地区">
                  <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                  <area-cascader :level="1" v-model="school.country" :data="pcaa"></area-cascader>
                  <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
                </el-form-item>
                <el-form-item label="联系地址">
                  <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                  <el-input placeholder="请输入联系地址" v-model="school.address" style="width:80%"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                  <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                  <el-input placeholder="请输入邮箱" v-model="school.zip_code" style="width:80%"></el-input>
                </el-form-item>

                <el-form-item label="单位网址">
                  <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                  <el-input placeholder="请输入单位网址" v-model="school.unit_url" style="width:80%"></el-input>
                </el-form-item>

                <el-form-item label="单位简介">
                  <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                  <el-input placeholder="请输入单位简介" v-model="school.major_platform" style="width:80%"></el-input>
                </el-form-item>
                <el-form-item label="重大平台">
                  <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                  <el-input placeholder="请输入重大平台" v-model="school.introduction" style="width:80%"></el-input>
                </el-form-item>
              </el-col>



            </el-row>
          </el-form>

        </el-tab-pane>

      </el-tabs>

  </div>

  <el-row>

      <div style="padding-left: 35%;margin: 40px 0  0  0;">
        <div class="" style="padding:15px" v-if="!$route.params.objData">
          <el-radio-group v-model="checkStatus">
            <el-radio :label="-1">草稿</el-radio>
            <el-radio :label="0">提交待审</el-radio>
            <el-radio :label="1">直接审核</el-radio>
          </el-radio-group>
        </div>
        <div class="">
          <el-button type="primary" style="width: 120px;" @click="saveFile(checkStatus)">保存</el-button>
          <el-button type="primary" style="width: 120px;" @click="back">返回</el-button>
        </div>

      </div>
  </el-row>


</div>
</template>

<script>
import 'vue-area-linkage/dist/index.css'; // v2 or higher
import contrary from '@/assets/images/QQ.png'
import contrary2 from '@/assets/images/QQ.png'
import {
  pca,
  pcaa
} from "area-data";
import {
  getAllrole,
} from '@/api/role'
import {
  getUserDetail,
  setUserDetail
} from '@/api/login'
import {
  getcollege,
  addLib
} from '@/api/library'

export default {
  data() {
    return {
      userType: '3',
      dateValue: '',
      contrary: '',
      contrary2: '',
      selected: [],
      titleName: '',
      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区
      service_amount_lastt: '',
      service_amount_beforet: '',
      service_amount_previoust: '',
      service_quantity_lastt: '',
      service_quantity_beforet: '',
      service_quantity_previoust: '',
      service_research_lastt: '',
      service_research_beforet: '',
      service_research_previoust: '',
      school: {
        zhengben: [],
        fuben: [],
        logo: [],
        introduction: '',
        major_platform: '',
        unit_url: '',
        zip_code: '',
        address: '',
        country: '',
        code: '',
        name: '',
      },
      checkStatus:-1
    }
  },
  async mounted() {
    if (this.$route.params.objData) {
      this.school = JSON.parse(this.$route.params.objData)
    }
  },
  computed: {},
  methods: {


      back() {
        window.history.go(-1);
      },
    async saveFile(checkStatus) {
      // if (!this.validata.validaRole(obj)) return
        let arr ={}
        arr.formType = '4'
        arr.checkStatus = checkStatus
        arr.id = this.$route.params.objId
        arr.detail = JSON.stringify(this.school)
        let {
          data,
          success,
          message
        } = await addLib(arr)
        if (success) {
          this.$message({
            message: '保存成功',
            type: 'success'
          });
          this.dialogFormVisible = false
          this.loadPageList()
        } else {
          this.$message({
            message: data.message,
            type: 'success'
          });
        }

    },


    async handleEdit(data, type) {
      if (type === 'edit') {
        this.obj = data
        this.dialogStatus = 'update'
        this.dialogsave = true
        this.dialogadd = false
        this.dialogFormVisible = true
      } else if (type === 'del') {
        this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          let del = await delRole(data.id)
          this.list.splice(this.list.indexOf(data), 1)
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          // this.splice(data.id, 1);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    },
    addProjectexpert() {
      this.expert.research_record.push({
        projectname: '',
        projectSrc: '',
        finishcon: '',
        finishtime: '',
        rewname: '',
        rewlevel: '',
        rewtime: ''
      })
    },
    addProjectcom() {
      this.com.comPorcolumnDefinitions.push({
        name: '',
        time: '',
        source: ''
      })
    },
    addProjectmech() {
      this.mech.PorcolumnDefinitions.push({
        name: '',
        time: '',
        source: ''
      })
    },
    addhonrmech() {
      this.mech.honor.push({
        name: '',
        time: '',
        issuingAgency: ''
      })
    },
    addcjmech() {
      this.mech.workrPorcolumnDefinitions.push({
        name: '',
        object: '',
        time: ''
      })
    },
  }
}
</script>

<style lang="scss">
@import '../../../styles/index.scss'; // 全局自定义的css样式
</style>

<style>
.tab-container {
  padding: 30px;
  overflow: auto;
}

.tools {
  height: 5%
}

.area-select .area-selected-trigger {
  position: relative;
  display: block;
  font-size: 14px;
  cursor: pointer;
  margin: 0;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  height: 100%;
  padding: 0px 0px 0px 12px;
}
</style>
