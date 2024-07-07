
    
  <script>
  import axios from 'axios';
  export default {
    data() {
      return {
        login_content: 'login_content',
        login_id: 'login-form',
        login_name: 'login-form',
        loginUserNumber: '',
        loginPassword: '',
        loginError: '',
        btnLogin: 'btnLogin',
        errors: 'errors',
        login_container: 'login_container',
        item: 'item',
        content: 'content',
        header: 'header',
      };
    },
    methods: {
      
      onBtnloginClick() {
        if (this.checkPwdLen() && this.checkUserNum()) {
          let userNumber = this.loginUserNumber;
          let userPwd = this.loginPassword;
          let URLS = "/logincontroller/loginCheck";
          let data = {
            code: 0,
            data: [],
            msg: ''
          }
          axios
            .post(URLS, {
              username: userNumber,
              password: userPwd,
            })
            .then((res) => {
              data.code = res.data.code;
              data.msg = res.data.msg;
              data.data = res.data.data;
              if (data.code == 1) {
                this.$store.dispatch('loginInfo/saveisTeacher', data.data[0].is_teacher);
                console.log(data.data[0].is_teacher);
                this.$store.dispatch('loginInfo/saveUserNumber', userNumber);
  
                let gotoAddress = data.data[0].is_teacher === 1 ? '/selectcourse' : '/selectstuclass';
                this.$router.push(gotoAddress);
              } else {
                this.loginError = '登录失败';
              }
            })
            .catch((error) => {
              this.loginError = '请检查网络连接';
              console.log(error);
            });
        }
      },
    },
  };
  </script>
