let vm = new Vue({
    el:'#main-container',
    data:function() {
        return {
            pageInfo: {
                pageNum: 1,
                pageSize: 5
            },//接受后台页对象
            map:{
                type:''//设置默认值
            },
            setting: {
                data: {//数据设置
                    //zTree可以通过传入一维数组来实现自动组装成树形结构（自动转多维数组遍历）
                    simpleData: {
                        enable: true,//设置简单数据格式，可以使用一维数组节点数据
                        pIdKey: 'parentId'//指定父节点id关联属性名
                    }
                },
                callback: {//绑定onClick回调事件
                    //当data进行绑定this.onClick函数对象的时候   this的onClick函数还没有初始化  必须将data设置为function回调
                    onClick: this.onClick
                },
                view:{
                    //显示节点的时候都会调用该属性对样式进行赋值，返回json格式
                    fontCss: this.fontCss
                }
            },
            node: [],
            nodeName:'',
            officeName:'全部'
        }
    },
    methods:{
        selectPage:function (pageNum,pageSize) {
            axios({
                url:`manager/examine/selectPage/${pageNum}/${pageSize}`,
                method:'post',
                data:this.map
            }).then( response => {
                this.pageInfo = response.data.obj;
                console.log(this.pageInfo)
            }).catch(error =>{
                layer.msg(error.message);
            })

        },
        selectAll:function () {
            this.map={type:''}
            this.selectPage(1,this.pageInfo.pageSize);
        },
        //initTree(obj,setting,nodes)
        //obj:挂载zTree的dom结点  jq选择器选择元素
        //setting:zTree的设置
        //node:节点对象数组
        initTree:function () {
            axios({
                url: 'manager/office/list'
            }).then(response => {
                this.node = response.data.obj;
                this.node[this.node.length] = {id: 0, name: '全部', open: true} //动态在nodes最后添加一个父节点数据
                let zTreeObj = $.fn.zTree.init($("#pullDownTreeone"), this.setting, this.node);
                //console.log(zTreeObj);
            }).catch(error=>{
                layer.msg(error.message);
            })
        },
        onClick: function (event,treeId,treeNode) {//event:事件对象 treeId树对象的id:treeDemo treeNode:被点击的节点
            //console.log(treeNode.name);
            this.officeName = treeNode.name;
            //给map赋值
            if(treeNode.id != 0){
                this.map.officeId = treeNode.id;
            }else {
                this.map.officeId= '';//查全部office
            }
        },
        findNames:function () {
            //1.模糊查询:从所有节点名中匹配节点
            let zTreeObj= $.fn.zTree.getZTreeObj("pullDownTreeone")//根据树的id查询树对象
            //getNodesByParamFuzzy(key,value,parentNode)  key:属性名   value：属性值  parentNode父节点
            let nodesByParamFuzzy = zTreeObj.getNodesByParamFuzzy("name",this.nodeName,null); //这里的null意指从所有的节点查找
            console.log(nodesByParamFuzzy);
            //2.高亮查询到对应节点
            let nodes=zTreeObj.transformToArray(zTreeObj.getNodes());//获取全部节点数据，将 zTree 节点数据转换为简单的一维数组结构
            for (let i = 0 ;i<nodes.length;i++){
                //初始化所有高亮节点
                nodes[i].hightLight = false;
                zTreeObj.updateNode(nodes[i]);
            }
            for(let i in nodesByParamFuzzy){
                for (let j = 0;j<nodes.length;j++){
                    //1.给所有高亮的节点添加高亮标记
                    //2.从原nodes中查找到对应的节点 更新节点 更新树上的节点属性内容
                    if(nodesByParamFuzzy[i].id===nodes[j].id){
                        nodes[j].hightLight=true;
                        zTreeObj.updateNode(nodes[j]);
                        break;//结束循环
                    }
                }
            }
        },
        fontCss:function (treeId,treeNode) {
            return treeNode.hightLight?{color:"red"}:{color:"black"};
        }
    },
    created:function () {
        this.selectPage(1,this.pageInfo.pageSize)
    },
    mounted:function () {
        this.initTree();//当vue接管el创建的节点挂载到页面后触发   原生div->data初始化了->created ->mounted（el） 创建ztree
    }
})