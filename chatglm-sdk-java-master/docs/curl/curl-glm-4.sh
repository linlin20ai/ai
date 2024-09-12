curl -X POST \
        -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsInNpZ25fdHlwZSI6IlNJR04ifQ.eyJhcGlfa2V5IjoiZmM1ZTExYWMyYWFiODgxODI0MDY4OWFhZTZmZGE4NGYiLCJleHAiOjE3MjYwNjM2Njk3NDAsInRpbWVzdGFtcCI6MTcyNjA2MTg2OTc0MH0.leXw4QsT5HppwXfdyBN7OCLOW_zXBEEBauELTmcxubs" \
        -H "Content-Type: application/json" \
        -H "User-Agent: Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)" \
        -d '{
          "model":"glm-4",
          "stream": "true",
          "messages": [
              {
                  "role": "user",
                  "content": "写个java冒泡排序"
              }
          ]
        }' \
  https://open.bigmodel.cn/api/paas/v4/chat/completions