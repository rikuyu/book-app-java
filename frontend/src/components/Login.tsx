import {Link} from "react-router-dom";
import {useState} from "react";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    return (
        <div className="flex justify-center items-center h-screen">
            <div className="flex flex-col items-center space-y-6 p-8 bg-white shadow-xl rounded-lg w-100 border border-gray-200">
                <h1 className="text-2xl font-bold">Login Page</h1>
                <input
                    type="text"
                    placeholder="ユーザーネーム"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    className="w-80 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 ring-green-200"
                />
                <input
                    type="password"
                    placeholder="パスワード"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="w-80 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 ring-green-200"
                />
                <button className="w-80 px-4 py-2 bg-green-600 text-white rounded-lg shadow-md hover:bg-green-500 transition">
                    ログイン
                </button>

                <Link to="/register">
                    <button className="w-80 px-4 py-2 border border-gray-400 text-gray-700 rounded-lg hover:bg-gray-100 transition">
                        新規登録
                    </button>
                </Link>
            </div>
        </div>
    );
}

export default Login;