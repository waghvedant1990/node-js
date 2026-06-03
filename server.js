app.get("/", (req, res) => {
    res.send(`
    <!DOCTYPE html>
    <html>
    <head>
        <title>DevOps CI/CD Demo</title>
        <style>
            body {
                margin: 0;
                font-family: Arial, sans-serif;
                background: linear-gradient(135deg, #1e3c72, #2a5298);
                color: white;
                text-align: center;
                padding-top: 100px;
            }

            .container {
                width: 80%;
                margin: auto;
            }

            h1 {
                font-size: 3rem;
                margin-bottom: 20px;
            }

            p {
                font-size: 1.3rem;
            }

            .card {
                background: rgba(255,255,255,0.15);
                padding: 30px;
                border-radius: 15px;
                margin-top: 30px;
                backdrop-filter: blur(10px);
            }

            .btn {
                display: inline-block;
                margin-top: 20px;
                padding: 12px 25px;
                background: #00d4ff;
                color: black;
                text-decoration: none;
                border-radius: 8px;
                font-weight: bold;
            }

            .footer {
                margin-top: 50px;
                color: #ddd;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>🚀 NodeJS CI/CD Pipeline</h1>

            <div class="card">
                <h2>Successfully Deployed Using Jenkins & Docker</h2>

                <p>
                    This application is automatically built, tested,
                    containerized, and deployed through a Jenkins Pipeline.
                </p>

                <p>
                    Technologies: NodeJS | Jenkins | Docker | GitHub
                </p>

                <a href="/health" class="btn">Health Check</a>
            </div>

            <div class="footer">
                <p>Created by Vedant Wagh</p>
            </div>
        </div>
    </body>
    </html>
    `);
});