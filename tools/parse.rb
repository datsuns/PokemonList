# coding: utf-8

require 'nokogiri'

STORE_DIR = './html/'

FORMAT = '
No.$number $title
<big>
  <br>
  <br>
  出現場所・入手方法
</big>
<br>
<br>
<table style="text-align: left; height: 230px; width: 600px;" border="1"cellpadding="0" cellspacing="0">
  <tbody>
    <tr>
      <td style="vertical-align: top; width: 130px;">
        <small>
          【ダイヤモンド】
          <br>
          【パール】
          <br>
          【プラチナ】
        </small>
      </td>
      <td style="vertical-align: top; width: 400px;">
        $diamond_area
        <br>
      </td>
    </tr>
    <tr>
      <td style="vertical-align: top; width: 130px;">
        <small>
          【ハートゴールド】
          <br>
          【ソウルシルバー】
        </small>
      </td>
      <td style="vertical-align: top; width: 450px;">
        $heart_gold_area
        <br>
      </td>
    </tr>
    <tr>
      <td style="vertical-align: top; width: 130px;">
        <small>
          【ブラック】
          <br>
          【ホワイト】
        </small>
      </td>
      <td style="vertical-align: top; width: 450px;">
        $black_area
      </td>
    </tr>
    <tr>
      <td style="vertical-align: top; width: 130px;">
        <small>
          【ブラック２】
          <br>
          【ホワイト２】
        </small>
      </td>
      <td style="vertical-align: top; width: 450px;">
        $black_2_area
      </td>
    </tr>
  </tbody>
</table>
'

def parse filename
  html = ''
  File.open(filename).readlines.each{ |l| html += l }
  doc = Nokogiri::HTML html

  # number
  num = doc.xpath('//table/tbody/tr/th').first.inner_html.split(' ')[1].gsub(/（.*/, '').gsub('No.', '')

  # name
  name = doc.xpath('//h1').inner_html

  # area parsing
  get_area_table = lambda{ |doc|
    doc.xpath('//div/h2').each{|v|
      return v.xpath('//dl') if v.inner_html.match '出現'
    }
    nil
  }

  get_area_titles = lambda{ |doc|
    doc.xpath('//div/h2').each{|v|
      return v.xpath('//dt') if v.inner_html.match '出現'
    }
    nil
  }

  entry = Struct.new :title, :body
  area_list = []

  titles = get_area_table.call(doc).xpath('//dt')
  areas = get_area_table.call(doc).xpath('//dd')

  cnt = 0
  titles.each do |title|
    data = title.inner_html.split('】')
    for i in 0..data.length - 1
      area_list << entry.new(data[i].gsub(/【| /, ''), areas[cnt])
    end
    cnt += 1
  end

  # formatting
  result = ''
  result << FORMAT
  result.gsub! '$number', num 
  result.gsub! '$title',  name
  area_list.each do |s|
    body = (s.body == nil) ? '' : s.body
    if s.title == 'ダイヤモンド'
      result.gsub! '$diamond_area', body
    end
    if s.title == 'ハートゴールド'
      result.gsub! '$heart_gold_area', body
    end
    if s.title == 'ブラック'
      result.gsub! '$black_area', body
    end
    if s.title == 'ブラック２'
      result.gsub! '$black_2_area', body
    end
  end
  result.gsub /\r|\n/, ''
end

def parse_local_file
  output = File.open 'index.html', 'w'
  Dir.entries(STORE_DIR).each do |entry|
    next if not File.extname(entry).match 'html'
    output << parse(File.expand_path(STORE_DIR + entry))
    output << "\n"
  end
  output.close
end

parse_local_file

