--
-- fmockup - Data Manipulation Language file for test
--
-- Date	   : 2023-10-01
-- Auther  : Hirotoshi FUJIBE
-- History :
--
-- Copyright (c) 2023 Hirotoshi FUJIBE
--

-- If error has occurred in here, have to re-run fmockup_dml. 

-- M_USER_LIST

INSERT INTO FMOCKUP.M_USER_LIST
	( USER_ID, USER_NAME, PASSWORD_AES, REMARK, ROLE_F, USER_LOCKED_F, FAILED_COUNT, LAST_LOGIN_DATE, LAST_CHANGE_PASSWORD_DATE, ADD_USER_DATE, MODIFY_USER_DATE, CREATE_DATE, UPDATE_DATE, DELETED_F )
	VALUES
		( '2023-01-01T00:00:00.000002', 'xxxx-user', HEX(AES_ENCRYPT('Asdf1234', 'Asdf1234Asdf1234')), 'Xxxx User', 'U', 'Y', 0, '2023-01-01 00:00:01', '1970-01-01 00:00:00', '2023-01-01 00:00:00', '2023-01-01 00:00:00', '2023-01-01T00:00:00.000000', '2023-01-01T00:00:01.000000', 'N' ),
		( '2023-01-01T00:00:00.000003', 'yyyy-user', HEX(AES_ENCRYPT('Asdf1234', 'Asdf1234Asdf1234')), 'Yyyy User', 'U', 'Y', 0, '2023-01-01 00:00:01', '1970-01-01 00:00:00', '2023-01-01 00:00:00', '2023-01-01 00:00:00', '2023-01-01T00:00:00.000000', '2023-01-01T00:00:01.000000', 'N' );

-- M_ROLE_LIST

INSERT INTO FMOCKUP.M_ROLE_LIST
	( ROLE_ID, ROLE_NAME, REMARK, ROLE_F, ADD_ROLE_DATE, MODIFY_ROLE_DATE, CREATE_DATE, UPDATE_DATE, DELETED_F )
	VALUES
		( '2023-01-01T00:00:00.000002', 'xxxx-role', 'Xxxx Role', 'U', '2023-01-01 00:00:00', '2023-01-01 00:00:00', '2023-01-01T00:00:00.000000', '2023-01-01T00:00:00.000000', 'N'),
		( '2023-01-01T00:00:00.000003', 'yyyy-role', 'Yxxx Role', 'U', '2023-01-01 00:00:00', '2023-01-01 00:00:00', '2023-01-01T00:00:00.000000', '2023-01-01T00:00:00.000000', 'N');

-- M_USER_ROLE_LIST

INSERT INTO FMOCKUP.M_USER_ROLE_LIST
	( USER_ID, ROLE_ID, CREATE_DATE, UPDATE_DATE, DELETED_F )
	VALUES
		( '2023-01-01T00:00:00.000002', '2023-01-01T00:00:00.000002', '2023-01-01T00:00:00.000000', '2023-01-01T00:00:00.000000', 'N' ),
		( '2023-01-01T00:00:00.000003', '2023-01-01T00:00:00.000003', '2023-01-01T00:00:00.000000', '2023-01-01T00:00:00.000000', 'N' );

-- M_ROLE_TRAN_LIST

INSERT INTO FMOCKUP.M_ROLE_TRAN_LIST
	( ROLE_ID, TRAN_ID, CREATE_DATE, UPDATE_DATE, DELETED_F )
	VALUES( '2023-01-01T00:00:00.000002', '2023-01-01T00:00:00.000701', '2023-01-01T00:00:00.000000', '2023-01-01T00:00:00.000000', 'N');
INSERT INTO FMOCKUP.M_ROLE_TRAN_LIST
	( ROLE_ID, TRAN_ID, CREATE_DATE, UPDATE_DATE, DELETED_F )
	VALUES( '2023-01-01T00:00:00.000003', '2023-01-01T00:00:00.000702', '2023-01-01T00:00:00.000000', '2023-01-01T00:00:00.000000', 'N');

-- T_XXXX_LIST

INSERT INTO FMOCKUP.T_XXXX_LIST
	( XXXX_ID, XXXX_NAME, XXXX_FLAG, XXXX_VALUE, REMARK, ADD_XXXX_DATE, MODIFY_XXXX_DATE, CREATE_DATE, UPDATE_DATE,	USER_ID, DELETED_F )
	VALUES( '2023-01-01T00:00:00.000001', 'XXXX', 'Y', 'XxxxXxxx', 'Xxxx Xxxx Xxxx', '2023-01-01 00:00:00', '2023-01-01 00:00:00', '2023-01-01T00:00:00.000000', '2023-01-01T00:00:00.000000', '2023-01-01T00:00:00.000002', 'N' );

-- T_YYYY_LIST

INSERT INTO FMOCKUP.T_YYYY_LIST
	( YYYY_ID, YYYY_NAME, YYYY_FLAG, YYYY_VALUE, REMARK, ADD_YYYY_DATE, MODIFY_YYYY_DATE, CREATE_DATE, UPDATE_DATE,	USER_ID, DELETED_F )
	VALUES( '2023-01-01T00:00:00.000001', 'YYYY', 'Y', 'YyyyYyyy', 'Yyyy Yyyy Yyyy', '2023-01-01 00:00:00', '2023-01-01 00:00:00', '2023-01-01T00:00:00.000000', '2023-01-01T00:00:00.000000', '2023-01-01T00:00:00.000003', 'N' );
