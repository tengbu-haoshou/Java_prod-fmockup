import { resolve } from 'path';
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
	plugins: [
		vue(),
	],
	build: {
		rollupOptions: {
			input: {
				about: resolve(__dirname, './pages/about.html'),
				home: resolve(__dirname, './pages/home.html'),
				login: resolve(__dirname, './pages/login.html'),
				login_expired: resolve(__dirname, './pages/login_expired.html'),
				password: resolve(__dirname, './pages/password.html'),
				role_list: resolve(__dirname, './pages/role_list.html'),
				user_list: resolve(__dirname, './pages/user_list.html'),
				xxxx_list: resolve(__dirname, './pages/xxxx_list.html'),
				yyyy_list: resolve(__dirname, './pages/yyyy_list.html'),
				zzzz_list: resolve(__dirname, './pages/zzzz_list.html'),
			}
		}
	},
})
