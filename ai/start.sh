docker run -p 8080:8080 --name ai-api -d shengtaolin/ai-api -e PARAMS="--sever.port=80 --chatgpt.host=https://sdk.xfg.im/ --chatgpt.apiKey= --wx.config.originalid=gh_c5ce6e4a0e0e  --wx.config.appid=wxad979c0307864a66 --wx.config.gatewayAddress=b8b6" -d fuzhengwei/chatgpt-api: 1.6



