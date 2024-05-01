<template>
	<v-app>
		<common_header :hover="hover" @header_event="receive_header" />
		<div v-if="display == ''">
			<v-main>
			</v-main>
		</div>
		<div v-if="display == 'error'">
			<v-main>
				<v-container fluid>
					<v-row no-gutters>
						<v-col><div style="color: red;">{{ message }}</div></v-col>
					</v-row>
				</v-container>
			</v-main>
		</div>
		<div v-if="display == 'normal'">
			<v-main>
				<v-container fluid>
					<v-row no-gutters>
						<v-col cols="12">
							<v-tabs v-model="tab">
								<v-tab style="text-transform: none" href="/pages/user_list.html" value="UserList"><h3 class="display-1">{{ text_tab_user_list }}</h3></v-tab>
								<v-tab style="text-transform: none" href="/pages/role_list.html" value="RoleList"><h3 class="display-1">{{ text_tab_role_list }}</h3></v-tab>
							</v-tabs>
						</v-col>
					</v-row>
					<v-row no-gutters>
						<v-col><div style="color: red;">{{ message }}</div></v-col>
					</v-row>
					<v-row>
						<v-col>
							<v-data-table :headers="headers" :items="items">
								<template v-slot:item.roleId="{ item }" />			<!-- to hide -->
								<template v-slot:item.updateDate="{ item }" />		<!-- to hide -->
								<template v-slot:item.no="{ item }">
									<div align="right">{{ item.no }}</div>
								</template>
								<template v-slot:item.roleName="{ item }">
									<div align="left">{{ item.roleName }}</div>
								</template>
								<template v-slot:item.remark="{ item }">
									<div align="left">{{ item.remark }}</div>
								</template>
								<template v-slot:item.actions="{ item }">
									<dialog_role v-if="item.deletedF == 'N'" :mode="modify_mode" :token="token" :roleId="item.roleId" :updateDate="item.updateDate" />
									<dialog_role v-if="item.deletedF == 'N'" :mode="drop_mode" :token="token" :roleId="item.roleId" :updateDate="item.updateDate" />
									<dialog_role_tran v-if="item.deletedF == 'N'" :mode="assign_mode" :token="token" :roleId="item.roleId" :updateDate="item.updateDate" />
									<dialog_role_tran v-if="item.deletedF == 'N'" :mode="unassign_mode" :token="token" :roleId="item.roleId" :updateDate="item.updateDate" />
								</template>
								<template v-slot:top>
									<dialog_role :mode="add_mode" :token="token" :roleId="space" :updateDate="space" />
								</template>
							</v-data-table>
						</v-col>
					</v-row>
				</v-container>
			</v-main>
		</div>
	</v-app>
	<common_texts @texts_event="receive_texts" />
	<common_token @token_event="receive_token" />
</template>

<style scoped>
.v-data-table {white-space: nowrap;}
.v-data-table th {text-align: center;}
</style>

<script setup>
	import axios from 'axios'
	import { ref } from 'vue'

	const hover = ref('RoleList')
	const message = ref('')

	const loading = ref(true)
	const display = ref('')
	const tab = ref('RoleList')

	const dialog = ref(false)
	const mode = ref('')
	const items = ref([])

	const add_mode = ref('add')
	const modify_mode = ref('modify')
	const drop_mode = ref('drop')
	const assign_mode = ref('assign')
	const unassign_mode = ref('unassign')
	const space = ref('')

	const text_tab_user_list = ref('')
	const text_tab_role_list = ref('')
	import common_texts from './commons/texts.vue'
	function receive_texts(texts_status, texts_message, texts_value) {
		if (texts_status == 'action-ok') {
			const texts_json = texts_value
			text_tab_user_list.value = texts_json.role_list_tab_user_list
			text_tab_role_list.value = texts_json.role_list_tab_role_list
		} else {
			display.value = 'error'
			message.value = texts_message
		}
	}

	import common_header from './commons/header.vue'
	function receive_header(header_status, header_message) {
		if (header_status != 'action-ok') {
			display.value = 'error'
			message.value = header_message
		}
	}

	const token = ref('')
	import common_token from './commons/token.vue'
	function receive_token(token_status, token_message, token_value) {
		if (token_status == 'action-ok') {
			token.value = token_value
		} else {
			display.value = 'error'
			message.value = token_message
		}
	}

	import dialog_role from './dialogs/role.vue'
	import dialog_role_tran from './dialogs/role_tran.vue'

	const headers = [
//		{title: 'Role ID (Hidden)', key: 'roleId', align: 'center'},				// hidden
//		{title: 'Update Datetime (Hidden)', key: 'updateDate', align: 'center'},	// hidden
		{title: 'No.', key: 'no', align: 'center'},
		{title: 'Actions', key: 'actions', align: 'center', sortable: false },
		{title: 'Role Name', key: 'roleName', align: 'center'},
		{title: 'Deteted', key: 'deletedF', align: 'center'},
		{title: 'Remark', key: 'remark', align: 'center'},
		{title: 'Modify Datetime', key: 'modifyRoleDate', align: 'center'},
		{title: 'Add Datetime', key: 'addRoleDate', align: 'center'},
	]

	const formData = new FormData()
	axios.get('/role/list', formData)
	.then (function(response) {
		if (response.data.status == 'action-ok') {
			items.value = response.data.list
		} else {
			display.value = 'error'
			message.value = response.data.message
		}
	})
	.catch (function(error) {
		loading.value = false
		display.value = 'error'
		message.value = 'Network trouble has occurred.'
	})

	loading.value = false
	if (display.value == '') {
		display.value = 'normal'
	}
</script>
