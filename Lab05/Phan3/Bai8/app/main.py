from flask import Flask, request
from tasks import add

app = Flask(__name__)

@app.route('/')
def index():
    return "<h1>Chào mừng tới Lab 5 - Celery + Redis!</h1><p>Dùng /add?x=10&y=20 để test task.</p>"

@app.route('/add')
def call_add():
    x = int(request.args.get('x', 0))
    y = int(request.args.get('y', 0))
    # Gửi task tới Celery
    task = add.delay(x, y)
    return f"🚀 Đã gửi task cộng {x} + {y}! ID task: {task.id}"

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8000)
