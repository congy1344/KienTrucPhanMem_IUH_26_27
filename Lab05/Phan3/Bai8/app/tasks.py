from celery import Celery
import os
import time

# Thiết lập Broker Redis
app = Celery('tasks', broker=os.environ.get('CELERY_BROKER_URL', 'redis://redis:6379/0'))

@app.task
def add(x, y):
    time.sleep(5) # Giả lập tác vụ nặng
    return x + y
