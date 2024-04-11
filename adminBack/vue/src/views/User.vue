<template>
  <div>
    <div style="margin: 10px 0">
            <el-input style="width: 200px;margin-right: 5px" placeholder="请输入用户名" suffix-icon="el-icon-search" v-model="username"></el-input>
            <el-input style="width: 200px;margin-right: 5px" placeholder="请输入地址" suffix-icon="el-icon-office-building" v-model="address"></el-input>
            <el-input style="width: 200px;margin-right: 5px" placeholder="请输入邮箱" suffix-icon="el-icon-message" v-model="email"></el-input>
            <el-button style="" type="primary" @click="load">搜索</el-button>
            <el-button style="" type="warning" @click="removeCondition">重置</el-button>
          </div>
          <div style="margin: 10px 0">
            <el-button type="primary">新增<i class="el-icon-circle-plus-outline" @click="add"></i></el-button>
            <el-popconfirm
                class="ml-5"
                width="220"
                confirm-button-text="是的"
                cancel-button-text="不了，谢谢"
                :icon="InfoFilled"
                icon-color="#626AEF"
                title="确认批量删除吗？"
                @confirm="delBatch"
            >
              <template #reference>
                <el-button type="danger">批量删除<i class="el-icon-delete"></i></el-button>
              </template>
            </el-popconfirm>
            <el-button type="primary" class="ml-5">导入<i class="el-icon-bottom-left"></i></el-button>
            <el-button type="primary">导出<i class="el-icon-top-right"></i></el-button>
          </div>
          <el-table :data="tableData" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" />
            <el-table-column prop="id" label="ID" width="80">
            </el-table-column>
            <el-table-column prop="username" label="用户名" width="140">
            </el-table-column>
            <el-table-column prop="nickname" label="昵称" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
            <el-table-column prop="email" label="邮箱" width="120">
            </el-table-column>
            <el-table-column prop="phone" label="手机号码" width="120">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
                <el-popconfirm
                    class="ml-5"
                    width="220"
                    confirm-button-text="是的"
                    cancel-button-text="不了，谢谢"
                    :icon="InfoFilled"
                    icon-color="#626AEF"
                    title="确认删除吗？"
                    @confirm="handleDelete(scope.row.id)"
                >
                  <template #reference>
                    <el-button type="danger">删除<i class="el-icon-delete"></i></el-button>
                  </template>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>
          <div style="padding: 10px 0">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[2, 5, 10, 15]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
          </div>
          <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="25%">
            <el-form :model="form" label-width=60px size="small">
              <el-form-item label="用户名">
                <el-input v-model="form.username" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="昵称">
                <el-input v-model="form.nickname" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <el-input v-model="form.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="地址">
                <el-input v-model="form.address" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="电话">
                <el-input v-model="form.phone" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="quitCommit">取 消</el-button>
              <el-button type="primary" @click="commitIngo">确 定</el-button>
            </div>
          </el-dialog>
  </div>
</template>

<script>
export default {
    name: "User",
    data(){
        return {
            tableData: [],
            multipleSelection: [],
            username: '',
            address: '',
            email: '',
            form: {},
            dialogFormVisible: false,
            total: 0,
            pageNum: 1,
            pageSize: 2,
        }
    },
    created() {
        this.load()
    },
    methods: {
        load() {
            this.request.get("/user/page", {
            params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            username: this.username,
            address: this.address,
            email: this.email
            }
        }).then(res => {
            console.log(res)
            this.tableData = res.records
            this.total = res.total
        })
        },
            removeCondition() {
            this.username = ''
        this.address = ''
        this.email = ''
        this.load()
        },
        // 多选操作
        handleSelectionChange(val) {
        this.multipleSelection = val
        },
        delBatch() {
        let ids = this.multipleSelection.map(v => v.id)  // [{},{},{}] => [1,2,3]
        this.request.post("/user/del/batch", ids).then(res => {
         if(res) {
             this.$message.success("批量删除成功！")
             this.load()
           } else {
             this.$message.error("删除失败，请重试！")
           }
         })
        },
        // 新增事件
        add() {
          this.dialogFormVisible = true
          this.form = {}
        },
        // 编辑信息(row中包含了id，传给后端可以精确编辑信息和新增，表单未显示id)
        handleEdit(row){
          this.form = Object.assign({},row)
          this.dialogFormVisible = true
        },
        // 删除信息
        handleDelete(id) {
          this.request.delete("/user/" + id).then(res => {
            if(res) {
              this.$message.success("删除成功！")
              this.load()
            } else {
              this.$message.error("删除失败，请重试！")
            }
          })
        },
        // 取消弹窗
        quitCommit() {
          this.dialogFormVisible = false
          this.load()
        },
        // 提交新用户表单
        commitIngo() {
          this.request.post("/user", this.form).then(res => {
            if(res) {
              this.$message.success("提交成功！")
              this.quitCommit()
            } else {
              this.$message.error("提交失败，请重试！")
            }
          })
        },
        handleSizeChange(pageSize) {
          this.pageSize=pageSize
          this.load()
        
        },
        handleCurrentChange(pageNum) {
          this.pageNum=pageNum
          this.load()
        }
    }
}
</script>

<style scoped>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>