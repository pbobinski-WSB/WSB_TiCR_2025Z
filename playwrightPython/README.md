python -m venv venv

venv\Scripts\activate

pip install -r requirements.txt

playwright install

pytest
pytest --headed
pytest --browser firefox
pytest --browser firefox --headed --slowmo 1000

npx playwright codegen
