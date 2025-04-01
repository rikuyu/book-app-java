# 📗書籍管理アプリ（🚧 開発停止中 🚧）
## 🔧 使用技術
| 💚 Backend        | 💙 Frontend    | 💛 その他        |
|-------------------|---------------|-----------------|
| Java              | React         | Github Actions  |
| Spring Boot       | Tailwind CSS  | Swagger         |
| Spring Security   | Vite          | JUnit           |
| MyBatis           | React Router  | Maven           |
| MySQL             | TypeScript    | Docker          |


## 🏚 ER図

```mermaid
erDiagram
    books {
        INT id PK "ID"
        VARCHAR(50) title "書籍タイトル"
        DATE published_at "出版年月日"
        ENUM status "available, borrowed" 
    }

    users ||--o{ borrow_records : ""
    users {
        INT id PK "ID"
        VARCHAR(50) name "氏名"
        VARCHAR(50) email "メールアドレス"
    }

    borrow_records }o--|| books : ""
    borrow_records {
        INT id PK "ID"
        INT book_id FK "書籍ID"
        INT user_id FK "ユーザーID"
        DATE borrowed_date "貸出日"
    }
```

## ⛩ API
### 🍩 書籍系
- `GET /books` ... 全ての本のリストを取得
- `GET /books/{id}` ... 特定の本の詳細を取得
- `POST /books` ... 新しい本を追加
- `DELETE /books/{id}` ... 特定の本を削除
- `GET /books/popular` ... 人気ランキング
- `GET /books/search?keyword="hoge"` ... 本の検索

### 🙎‍♂️ 会員系
- `GET /users` ... 全ての利用者のリストを取得
- `GET /users/{id}` ... 特定の利用者の詳細を取得
- `POST /users` ... 新しい利用者を登録
- `DELETE /users/{id}` ... 特定の利用者を削除
- `POST /users/image` ... プロフィール写真のアップロード  
- `GET /users/me` ... 認証済みの自分の情報取得
- `DELETE /users/me` ... 認証済みの自分の情報取得


### 📜 貸出記録系
- `GET /borrow_records` ... 全ての貸出記録を取得
- `GET /borrow_records/books` ... 全ての貸出記録を本で取得
- `GET /borrow_records/users` ... 全ての貸出記録を利用者で取得
- `POST /borrow_records` ... 貸出記録を追加（本の貸し出し）
- `PUT /borrow_records/{borrow_record_id}/books/{book_id}` ... 貸出記録を更新（本の返却）

### 🔐 認証系
- `POST /register` ... 新規登録
- `POST /login` ... ログイン
- `POST /logout` ... ログアウト

## 🔫 画面
### 🍉 新規登録
<img width="600" alt="register" src="https://github.com/user-attachments/assets/6c3eda48-0d12-479c-ab7c-ca76b8b0d0e8" />

### 🍔 ログイン
<img width="600" alt="login" src="https://github.com/user-attachments/assets/1fdd1b1e-8797-45d8-9f22-d76fba4987b1" />

### 🥝 マイページ
<img width="600" alt="マイページ" src="https://github.com/user-attachments/assets/8995cf9a-3070-47d7-9f08-8a53b5052f66" />

### 🍡 書籍一覧
<img width="1500" alt="書籍一覧" src="https://github.com/user-attachments/assets/77a56227-fc34-49b1-91d8-8f9055c2ed2f" />

### 🐣 書籍の検索
<img width="1500" alt="書籍の検索" src="https://github.com/user-attachments/assets/6f41b250-de74-454b-a5e6-c6d726a75169" />

### 🐶 書籍の人気ランキング
<img width="1500" alt="人気ランキング" src="https://github.com/user-attachments/assets/a85b0134-fa66-42be-97cf-19b9f27bf316" />

### 🌻 【管理者用】 書籍の追加
<img width="1500" alt="【管理者用】 書籍の追加" src="https://github.com/user-attachments/assets/c937b3eb-4e46-4da6-83ba-5aef6815be5e" />
  
### 🌺 【管理者用】 会員一覧
<img width="1500" alt="【管理者用】 会員一覧" src="https://github.com/user-attachments/assets/5dc9fa3c-e794-48ce-93a4-6f978f3a37c6" />

### 🐷 【管理者用】 貸出記録一覧
<img width="1500" alt="【管理者用】 貸出記録一覧" src="https://github.com/user-attachments/assets/3214cead-5691-4dfd-aca6-7f0107d81fa5" />

