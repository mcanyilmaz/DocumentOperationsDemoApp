# DocumentOperationsDemoApp
Document upload, download operations with Spring Boot DB and Local Storage

Dosya İşlemleri ( Yükleme İndirme Güncelleme Silme ) Projesi

Kullanıcı tarafından yüklenecek olan dosyalara ait bilgiler ( isim, uzantı, boyut, path ) Veritabanı üzerinde tutulmaktadır. 
Kullanıcı tarafından yüklenecek olan dosyalar local depolama alanında bir dosya path’inde tutulmaktadır. Bu dosyaların tutulduğu path statik olarak girilmiştir. Class üzerinden değiştirilebilir.
Yüklenecek olan dosyalar için boyut ve dosya tipi kontrolü yapılmıştır. Formata uymayan dosyalar veritabanına kaydolmayacaktır. 

Access token için hardcoded
 Username : testuser
 Pass : test123
şeklinde login apisi token dönmektedir. 

Api erişimi JWT ile güvenli hale getirilmiş olup, default bir kullanıcı tanımlanmıştır. Bu kullanıcıya ait username ve password bilgileri girilerek response olarak bir Token dönmektedir.  API çağırımları Headers’a Authorization : token set edilerek yapılmalıdır. 

Dosyaya erişim URL ve Byte[] üzerinden sağlanabilmektedir. 
Dosyaların tüm bilgileri tek seferde dönmektedir.
Dosya Id ye göre Local depolama alanından ve Veritabanı üzerinden silinmektedir.
Dosya Id ye göre değiştirilmek istendiğinde API çağırımı sırasında veritabanı ve local depolama  alanı üzerinde bulunan path üzerinde değiştirilmektedir.

Swagger projeye entegre edilmiştir.
Projeye ait API çağırımlarını temsilen Postman üzerinden atılan istekler aşağıda resimlerde paylaşılmıştır.


Upload Document API'sinin Postman Üzerinde Request Response Bilgisi Status = 200
![image](https://github.com/mcanyilmaz/DocumentOperationsDemoApp/assets/26096319/346e615f-6c6e-4fce-86e2-380717569883)

Upload Document API'sinin Postman Üzerinde Request Response Bilgisi Status = 400 Uygunsuz Format Hatası
![image](https://github.com/mcanyilmaz/DocumentOperationsDemoApp/assets/26096319/beb8af78-77c3-48d1-b6b7-7e33dbf574e3)

Kaydedilen dosyaların Database ve Local Storage alanında kayıtlı durumu
![image](https://github.com/mcanyilmaz/DocumentOperationsDemoApp/assets/26096319/8ff78b39-bb5a-475c-8be7-c0629203c594)

Database'den okunan Dosyalara ait bilgiler
![image](https://github.com/mcanyilmaz/DocumentOperationsDemoApp/assets/26096319/a7043cc7-02d7-4c0d-931a-b663d9308d7b)

Database'den okunan dosyanın Byte[] tipinde dönmüş hali

![image](https://github.com/mcanyilmaz/DocumentOperationsDemoApp/assets/26096319/c2adac73-f6a5-414c-b95e-eb45798bcbec)

Delete Document APİSİ Response
![image](https://github.com/mcanyilmaz/DocumentOperationsDemoApp/assets/26096319/a834e65d-4f31-4d22-8272-3c3ef16bdf20)

Delete Document APISI DB and Local Storage Kontrolü
![image](https://github.com/mcanyilmaz/DocumentOperationsDemoApp/assets/26096319/fa6dd8cc-f573-4f28-8a16-e616fc21d9f3)

Swagger 

![image](https://github.com/mcanyilmaz/DocumentOperationsDemoApp/assets/26096319/e503352a-86ea-4e22-be72-70d96164be08)


Postman API Çağırımı Örnek Request Response Pair;




{
	"info": {
		"_postman_id": "bcfde7a9-676b-4a21-8e48-0e3a8b1ec9c9",
		"name": "Document Upload And Download Demo Project",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "uploadDocument",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/v2/uploadDocument/",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"v2",
						"uploadDocument",
						""
					]
				}
			},
			"response": []
		},
		{
			"name": "getDocumentByName",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/v2/getDocumentByName/",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"v2",
						"getDocumentByName",
						""
					]
				}
			},
			"response": []
		},
		{
			"name": "deleteDocumentById",
			"request": {
				"method": "Delete",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/v2/deleteDocumentById/",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"v2",
						"deleteDocumentById",
						""
					]
				}
			},
			"response": []
		},
		{
			"name": "getAllDocument",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/v2/getAllDocument/",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"v2",
						"getAllDocument",
						""
					]
				}
			},
			"response": []
		},
		{
			"name": "updateDocument",
			"request": {
				"method": "POST",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/v2/updateDocument/",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"v2",
						"updateDocument",
						""
					]
				}

			},
			"response": []
		},
		{
			"name": "Login",
			"request": {
				"method": "POST",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/auth/login/",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"auth",
						"login",
						""
					]
				}

			},
			"response": []
		}
	]
}

