import { createStore, applyMiddleware } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import rootReducer from "../reducers/index";
import thunk from "redux-thunk";
import reduxImmutableStateInvariant from "redux-immutable-state-invariant";

export default function configureStore() {
  const initialState = {
    appPrefs: { lang: localStorage.getItem("lang"),
      headerName: "j4reef",
      codeType : 'WEB',
      debugClient: true,
      memberMenu: "MEMBER_MENU_RIGHT",
      adminMenu: "ADMIN_MENU_RIGHT",
      prefForms: {
          LOGIN_FORM: [
            {
              value: '',
              label: 'User name:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 0,
              validation: '{"regex":"^[a-zA-Z0-9_#!@.]*$","errorMsg":"Validation Error"}',
              name: 'LOGIN_FORM_USERNAME',
              fieldType: 'TXT',
              htmlType: 'text',
              group: 'MAIN',
              tabIndex: '1'
            },
            {
              value: '',
              label: 'Password:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 1,
              validation: '{"regex":"^(?=(.*[a-z]){1,})(?=(.*[A-Z]){1,})(?=(.*[\\\\d]){1,})(?=(.*[^a-zA-Z0-9]){1,}).{7,30}$","errorMsg":"Validation Error"}',
              name: 'LOGIN_FORM_PASSWORD',
              fieldType: 'TXT',
              htmlType: 'password',
              group: 'MAIN',
              tabIndex: '2'
            }
          ],
          REGISTRATION_FORM: [
            {
              value: '',
              label: 'First name:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 0,
              validation: '{"regex":"^[a-zA-Z0-9]*$","errorMsg":"Validation Error"}',
              name: 'REGISTRATION_FORM_FIRSTNAME',
              fieldType: 'TXT',
              htmlType: 'text',
              group: 'MAIN',
              tabIndex: '1'
            },
            {
              value: '',
              label: 'Middle name:',
              lang: 'en',
              rendered: false,
              required: false,
              order: 1,
              validation: '{"regex":"^[a-zA-Z0-9]*$","errorMsg":"Validation Error"}',
              name: 'REGISTRATION_FORM_MIDDLENAME',
              fieldType: 'TXT',
              htmlType: 'text',
              group: 'MAIN',
              tabIndex: '1'
            },
            {
              value: '',
              label: 'Last name:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 2,
              validation: '{"regex":"^[a-zA-Z0-9]*$","errorMsg":"Validation Error"}',
              name: 'REGISTRATION_FORM_LASTNAME',
              fieldType: 'TXT',
              htmlType: 'text',
              group: 'MAIN',
              tabIndex: '1'
            },
            {
              value: '',
              label: 'Zip code:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 3,
              validation: '{"regex":"^\\\\d{5}(?:[-\\\\s]\\\\d{4})?$","errorMsg":"Validation Error"}',
              name: 'REGISTRATION_FORM_ZIPCODE',
              fieldType: 'TXT',
              htmlType: 'text',
              group: 'MAIN',
              tabIndex: '1'
            },
            {
              value: '-Name used on posts-',
              label: 'User name:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 4,
              validation: '{"regex":"^[a-zA-Z0-9_#!@.]*$","errorMsg":"Validation Error"}',
              name: 'REGISTRATION_FORM_USERNAME',
              fieldType: 'TXT',
              htmlType: 'text',
              group: 'MAIN',
              tabIndex: '1'
            },
            {
              value: '-Valid email address-',
              label: 'Email:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 5,
              validation: '{"regex":"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$","errorMsg":"Validation Error"}',
              name: 'REGISTRATION_FORM_EMAIL',
              fieldType: 'TXT',
              htmlType: 'email',
              group: 'MAIN',
              tabIndex: '1'
            },
            {
              value: '',
              label: 'Password:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 6,
              validation: '{"regex":"^(?=(.*[a-z]){1,})(?=(.*[A-Z]){1,})(?=(.*[\\\\d]){1,})(?=(.*[!#@&*$%]){1,}).{7,30}$","errorMsg":"Validation Error","onFail":[{"regex":"(?=(.*[a-z]){1,})","link":"REGISTRATION_FORM_ALPHA_CHECK"},{"regex":"(?=(.*[A-Z]){1,})","link":"REGISTRATION_FORM_CAPITAL_CHECK"},{"regex":"(?=(.*[\\\\d]){1,})","link":"REGISTRATION_FORM_NUMBER_CHECK"},{"regex":"(?=(.*[!#@&*$%]){1,})","link":"REGISTRATION_FORM_SPECIAL_CHECK"},{"regex":".{7,30}","link":"REGISTRATION_FORM_COUNT_CHECK"}]}',
              name: 'REGISTRATION_FORM_PASSWORD',
              fieldType: 'TXT',
              htmlType: 'password',
              group: 'MAIN',
              tabIndex: '1'
            },
            {
              value: '',
              label: 'Verify Password:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 7,
              validation: '{"operator":"equals","matchField":"REGISTRATION_FORM_PASSWORD","errorMsg":"Password does not match"}',
              name: 'REGISTRATION_FORM_VERIFYPASSWORD',
              fieldType: 'TXT',
              htmlType: 'password',
              group: 'MAIN',
              tabIndex: '1',
              optionalParams: '{"matchItem":"REGISTRATION_FORM_PASSWORD"}'
            }
          ],
          PASSWORD_CHANGE_FORM: [
            {
              value: '',
              label: 'User name:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 0,
              validation: '{"regex":"^[a-zA-Z0-9_#!@.]*$","errorMsg":"Validation Error"}',
              name: 'PASSWORD_CHANGE_FORM_USERNAME',
              fieldType: 'TXT',
              htmlType: 'text',
              group: 'MAIN',
              tabIndex: '1'
            },
            {
              value: '',
              label: 'Old Password:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 1,
              validation: '{"regex":"^(?=(.*[a-z]){1,})(?=(.*[A-Z]){1,})(?=(.*[\\\\d]){1,})(?=(.*[^a-zA-Z0-9]){1,}).{7,30}$","errorMsg":"Validation Error"}',
              name: 'PASSWORD_CHANGE_FORM_OLD_PASSWORD',
              fieldType: 'TXT',
              htmlType: 'password',
              group: 'MAIN',
              tabIndex: '2'
            },
            {
              value: '',
              label: 'Password:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 1,
              validation: '{"regex":"^(?=(.*[a-z]){1,})(?=(.*[A-Z]){1,})(?=(.*[\\\\d]){1,})(?=(.*[^a-zA-Z0-9]){1,}).{7,30}$","errorMsg":"Validation Error"}',
              name: 'PASSWORD_CHANGE_FORM_PASSWORD',
              fieldType: 'TXT',
              htmlType: 'password',
              group: 'MAIN',
              tabIndex: '3'
            },
            {
              value: '',
              label: 'Verify Password:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 2,
              validation: '{"regex":"^(?=(.*[a-z]){1,})(?=(.*[A-Z]){1,})(?=(.*[\\\\d]){1,})(?=(.*[^a-zA-Z0-9]){1,}).{7,30}$","errorMsg":"Validation Error"}',
              name: 'PASSWORD_CHANGE_FORM_VERIFYPASSWORD',
              fieldType: 'TXT',
              htmlType: 'password',
              group: 'MAIN',
              tabIndex: '4',
              optionalParams: '{"matchItem":"PASSWORD_CHANGE_FORM_PASSWORD"}'
            }
          ],
          FORGOTPASSWORD_FORM: [
            {
              value: '',
              label: 'Username Or Email:',
              lang: 'en',
              rendered: true,
              required: true,
              order: 0,
              validation: '{"regex":"^[a-zA-Z0-9_#!@.]*$","errorMsg":"Validation Error"}',
              name: 'FORGOTPASSWORD_FORM_USERNAME',
              fieldType: 'TXT',
              htmlType: 'text',
              group: 'MAIN',
              tabIndex: '1'
            }
          ]
        },
        prefTexts: {
          LOGIN_FORM: {
            LOGIN_FORM_HEADER: {
              value: 'Great to see you!',
              lang: 'en',
              rendered: true,
              name: 'LOGIN_FORM_HEADER'
            },
            LOGIN_FORM_USERMISSING: {
              value: 'The user name you provided is not registered',
              lang: 'en',
              rendered: true,
              name: 'LOGIN_FORM_USERMISSING'
            },
            LOGIN_FORM_FORGOT_PASSWORD: {
              value: 'Forgot Password?',
              lang: 'en',
              rendered: true,
              name: 'LOGIN_FORM_FORGOT_PASSWORD'
            },
            LOGIN_FORM_REMEMBER_ME: {
              value: 'Remember Me',
              lang: 'en',
              rendered: true,
              name: 'LOGIN_FORM_REMEMBER_ME'
            },
            LOGIN_FORM_CHANGE_PASSWORD: {
              value: 'Change Password?',
              lang: 'en',
              rendered: true,
              name: 'LOGIN_FORM_CHANGE_PASSWORD'
            }
          },
          REGISTRATION_FORM: {
            REGISTRATION_FORM_HEADER: {
              value: 'Registration',
              lang: 'en',
              rendered: true,
              name: 'REGISTRATION_FORM_HEADER'
            },
            REGISTRATION_FORM_SUCCESSFUL: {
              value: 'User Registration Successful',
              lang: 'en',
              rendered: true,
              name: 'REGISTRATION_FORM_SUCCESSFUL'
            },
            REGISTRATION_FORM_NUMBER_CHECK: {
              value: 'At least one number',
              lang: 'en',
              rendered: true,
              name: 'REGISTRATION_FORM_NUMBER_CHECK'
            },
            REGISTRATION_FORM_CAPITAL_CHECK: {
              value: 'At least one capital',
              lang: 'en',
              rendered: true,
              name: 'REGISTRATION_FORM_CAPITAL_CHECK'
            },
            REGISTRATION_FORM_FAILED: {
              value: 'User Registration Failed',
              lang: 'en',
              rendered: true,
              name: 'REGISTRATION_FORM_FAILED'
            },
            REGISTRATION_FORM_COUNT_CHECK: {
              value: 'At least 8 characters',
              lang: 'en',
              rendered: true,
              name: 'REGISTRATION_FORM_COUNT_CHECK'
            },
            REGISTRATION_FORM_ALPHA_CHECK: {
              value: 'At least one alpha letter',
              lang: 'en',
              rendered: true,
              name: 'REGISTRATION_FORM_ALPHA_CHECK'
            },
            REGISTRATION_FORM_SPECIAL_CHECK: {
              value: 'At least one special characters !#@&*$%',
              lang: 'en',
              rendered: true,
              name: 'REGISTRATION_FORM_SPECIAL_CHECK'
            },
            REGISTRATION_FORM_MATCH_CHECK: {
              value: 'Match password',
              lang: 'en',
              rendered: true,
              name: 'REGISTRATION_FORM_MATCH_CHECK'
            }
          },
          GLOBAL_PAGE: {
            GLOBAL_PAGE_PAGING_FIRST: {
              value: 'First',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_PAGING_FIRST'
            },
            GLOBAL_PAGE_SEARCH_BUTTON: {
              value: 'Go!',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_SEARCH_BUTTON'
            },
            GLOBAL_PAGE_MENU_OPTION_DELETE: {
              value: 'Delete',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_MENU_OPTION_DELETE'
            },
            GLOBAL_PAGE_SEARCH_INPUT: {
              value: 'Search for...',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_SEARCH_INPUT'
            },
            GLOBAL_PAGE_PAGING_SHOW_ENTRIES: {
              value: 'Showing {listStart} to {listLimit} of {itemCount} entries',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_PAGING_SHOW_ENTRIES'
            },
            GLOBAL_PAGE_BUTTON_ACCEPT: {
              value: 'Save',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_BUTTON_ACCEPT'
            },
            GLOBAL_PAGE_FORM_HEADER_CREATE: {
              value: 'Create',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_FORM_HEADER_CREATE'
            },
            GLOBAL_PAGE_PAGING_NEXT: {
              value: 'Next',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_PAGING_NEXT'
            },
            GLOBAL_PAGE_PAGING_LAST: {
              value: 'Last',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_PAGING_LAST'
            },
            GLOBAL_PAGE_PAGING_PREV: {
              value: 'Prev',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_PAGING_PREV'
            },
            GLOBAL_PAGE_MENU_OPTION_ADD: {
              value: 'Add',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_MENU_OPTION_ADD'
            },
            GLOBAL_PAGE_LIST_EMPTY: {
              value: 'No items available',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_LIST_EMPTY'
            },
            GLOBAL_PAGE_BUTTON_DECLINE: {
              value: 'Cancel',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_BUTTON_DECLINE'
            },
            GLOBAL_PAGE_FORM_HEADER_MODIFY: {
              value: 'Modify',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_FORM_HEADER_MODIFY'
            }
          },
          PASSWORD_CHANGE_FORM: {
            PASSWORD_CHANGE_FORM_SUBMIT: {
              value: 'Submit',
              lang: 'en',
              rendered: true,
              name: 'PASSWORD_CHANGE_FORM_SUBMIT'
            }
          },
          FORGOTPASSWORD_FORM: {
            FORGOTPASSWORD_FORM_HEADER: {
              value: 'Forgot password',
              lang: 'en',
              rendered: true,
              name: 'FORGOTPASSWORD_FORM_HEADER'
            }
          }
        },
        prefLabels: {
          LOGIN_FORM: [
            {
              value: 'Log in',
              lang: 'en',
              rendered: true,
              order: 0,
              name: 'LOGIN_FORM_SUBMIT_BUTTON',
              className: 'form-control btn btn-login',
              tabIndex: '4'
            }
          ],
          REGISTRATION_FORM: [
            {
              value: 'Register',
              lang: 'en',
              rendered: true,
              order: 0,
              name: 'REGISTRATION_FORM_SUBMIT_BUTTON',
              className: 'form-control btn btn-register',
              tabIndex: '1'
            }
          ],
          FORGOTPASSWORD_FORM: [
            {
              value: 'Submit',
              lang: 'en',
              rendered: true,
              order: 0,
              name: 'FORGOTPASSWORD_FORM_SUBMIT_BUTTON',
              className: 'form-control btn btn-login',
              tabIndex: '1'
            }
          ]
        },
        prefOptions: {
          REGISTRATION_FORM: {
            REGISTRATION_SHOW_FORM: {
              value: 'true',
              lang: 'en',
              rendered: true,
              name: 'REGISTRATION_SHOW_FORM',
              valueType: 'Boolean',
              defaultValue: 'true',
              useDefault: false
            }
          },
          GLOBAL_PAGE: {
            GLOBAL_PAGE_LISTLIMIT: {
              value: '20',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_LISTLIMIT',
              valueType: 'Integer',
              defaultValue: '20',
              useDefault: false
            },
            GLOBAL_PAGE_LISTLIMIT_SELECT: {
              value: '[{"k":20,"v":20},{"k":50,"v":50},{"k":100,"v":100}]',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_LISTLIMIT_SELECT',
              valueType: 'JSON',
              defaultValue: '[{"k":20,"v":20}]',
              useDefault: false
            },
            GLOBAL_PAGE_LISTLIMIT_MAX: {
              value: '200',
              lang: 'en',
              rendered: true,
              name: 'GLOBAL_PAGE_LISTLIMIT_MAX',
              valueType: 'Integer',
              defaultValue: '200',
              useDefault: false
            }
          }
        },
        prefGlobal: {
          LANGUAGES: [
            {
              code: 'en',
              title: {
                defaultText: 'English',
                langTexts: [
                  {
                    lang: 'es',
                    text: 'Inglés'
                  },
                  {
                    lang: 'en',
                    text: 'English'
                  }
                ]
              },
              defaultLang: true,
              dir: 'ltr'
            },
            {
              code: 'es',
              title: {
                defaultText: 'Spanish',
                langTexts: [
                  {
                    lang: 'es',
                    text: 'Español'
                  },
                  {
                    lang: 'en',
                    text: 'Spanish'
                  }
                ]
              },
              defaultLang: false,
              dir: 'ltr'
            }
          ]
        }
      },
      appMenus: {
        MEMBER_MENU_RIGHT:[{menuId:1, code:'HOME', permissionCode:'MH', values:[{rendered:true,href:'member',value:'Home',image:'home_blue', lang:'en'}]},
                  {menuId:2, code:"PLUGS", permissionCode:"MFQ", values:[{rendered:true,href:"plugs",value:"Plugs",image:"test"}]},
                  {menuId:3, code:"LOG_OUT",permissionCode:"MLO",values:[{rendered:true,href:"member-logout",value:"Logout",image:"gears_blue"}]}
                ],
        ADMIN_MENU_RIGHT:[{menuId:1, code:'HOME', permissionCode:'AH', values:[{rendered:true,href:'member',value:'Home', lang:'en'}]},
        	{menuId:2, code:"STORE",permissionCode:"MS",values:[{rendered:true,href:"member-store",value:"Store"}]},
        	{menuId:3, code:"DASHBOARD", permissionCode:"AD", values:[{rendered:true,href:"admin",value:"Dashboard"}]},
            {menuId:4, code:"USERS", permissionCode:"AU", values:[{rendered:true,href:"admin-users",value:"Users"}]},
            {menuId:5, code:"INVENTORY",permissionCode:"AS",values:[{rendered:true,href:"admin-store",value:"Inventory"}]},
            {menuId:6, code:"PURCHASE",permissionCode:"AP",values:[{rendered:true,href:"admin-purchase",value:"Purchases"}]},
            {menuId:7, code:"TASKS",permissionCode:"AT",values:[{rendered:true,href:"admin-tasks",value:"Tasks"}]},
            {menuId:8, code:"IMPORT",permissionCode:"AI",values:[{rendered:true,href:"admin-import",value:"Import"}]}
          ],
        PUBLIC_MENU_RIGHT: []
      },
    session: { sessionActive: null },
    status: { error: null, info: null, warn: null }
  };
  return createStore(
    rootReducer,
    initialState,
    composeWithDevTools(applyMiddleware(thunk, reduxImmutableStateInvariant()))
  );
}
// user:localStorage.getItem("user")
