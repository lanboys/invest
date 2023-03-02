#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
mitmproxy -s E:\workspace\my-workspace\invest-workspace\invest\mitmproxy\qieman.py
mitmweb -s E:\workspace\my-workspace\invest-workspace\invest\mitmproxy\qieman.py
mitmdump -s E:\workspace\my-workspace\invest-workspace\invest\mitmproxy\qieman.py
"""
from mitmproxy import http
import json
import requests
 
def response(flow: http.HTTPFlow):
	# if flow.request.host != "qieman.com":
	# 	print("非白名单域名，跳过")
	# 	return
	# print(type(flow.request.url.startswith("https://qieman.com/pmdj/v2/long-win/ca/assets-summary")))
		
	
	
	#if not any(flow.request.url in item for item in white_url_list):
	#	print("非白名单链接，跳过")
	#	return
		
		
	white_url_list = ['https://qieman.com/pmdj/v2/long-win/ca/assets-summary',
	                    'https://qieman.com/pmdj/v2/asset/ca/detail',
	                    'https://qieman.com/pmdj/v2/wallet/turnovers']
	skip = True
	for item in white_url_list:
		if flow.request.url.startswith(item):
			skip = False
			break

	if skip:
		print("非白名单链接，跳过 " + flow.request.url)
		return
		
	#if not flow.request.url.startswith("https://qieman.com/pmdj/v2/long-win/ca/assets-summary"):
	#	print("非白名单链接，跳过")
	#	return
		
	print("-----开始传输数据-------")
	
	url = "http://localhost:6666/invest/api/mitmproxy/upload"
	
	payload = json.dumps({
		"host": flow.request.host,
		"url": flow.request.url,
		"statusCode": flow.response.status_code,
		"body": flow.response.get_text()
	})
	headers = {
	'Content-Type': 'application/json'
	}
	# print(payload)
	response = requests.request("POST", url, headers=headers, data=payload)
	
	print("返回值：" + response.text)
	print("-----数据传输完成-------")
 