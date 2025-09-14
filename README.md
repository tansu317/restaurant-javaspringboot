# restaurant-javaspringboot

Azarya Kairossutan Sacri Pusaka DAMI - Food Management System (Restaurant)

[START]

   |
   v
[REGISTER USER - Tanpa Authentication Token JWT]
   - User isi nama, email, username, password, role
   - System validasi data
   - System simpan user baru ke database
   - System return "Registrasi berhasil"

   |
   v
[LOGIN USER]
   - User kirim username & password
   - System validasi username & password
   - Jika valid → Generate JWT Token
   - Return Token ke user
   - Jika tidak valid → Return error

   |
   v
[AKSES MENU]
   - User kirim request POST /menu/find-all dengan token login
   - System validasi token
   - Jika valid → tampilkan daftar menu | menambah dan edit daftar menu
   - Jika tidak valid → return Unauthorized (401)

   |
   v
[BUAT ORDER]
   - User pilih menu & kirim request POST /order
   - System buat record order (status: pending)
   - Return detail order (orderId, total sementara)

   |
   v
[TAMBAH ORDER ITEM]
   - User kirim request POST /order-item
     dengan orderId & item detail (menuId, qty)
   - System simpan ke tabel order_item
   - Update total order
   - Return detail order terbaru

   |
   v
[END / ORDER SIAP DIPROSES]
