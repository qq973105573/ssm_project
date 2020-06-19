let vm = new Vue({
    el:'#main-container',
    data:{
        pageInfo:{
            pageSize:5
        },
        condition:{
            type:'',
            check:''
        }
    },
    methods:{
        selectPage:function (pageNum,pageSize) {
            axios({
                url:`manager/qualification/selectPage/${pageNum}/${pageSize}`,
                method:'post',
                data:this.condition
            }).then( response => {
                this.pageInfo = response.data.obj;
                console.log(this.pageInfo)
            }).catch(error =>{
                console.log(error);
            })

        },
        selectAll:function () {
            this.condition={type:'',check:''}
            this.selectPage(1,this.pageInfo.pageSize);
        }
    },
    created:function () {
        this.selectPage(1,this.pageInfo.pageSize)
    }
})