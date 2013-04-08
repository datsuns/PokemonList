# coding: utf-8
#
# TODO
#  * htmlの分解
#  * レイアウトの出力機能
#  * レイアウトに合わせて出力
#
# * rubyでhtmlを操作するクラスがあるはず
#   -> Hpricot
require 'hpricot'
require 'open-uri'

HEADER = "<table border=4  align=center>\n"
FOOTER = "</table>\n"

def pick_name text
  text.split('全国')[1].split("\n")[0].chomp
end

def pick_encount_list text
  list = text.split('出現場所・入手方法')[1].split('育成論')[0].split("\n")
  list.delete("")
  list
end


def parse filename
=begin
# hpricotお試し
  #doc = Hpricot( open("http://www.google.co.jp/").read )
  text = Hpricot( File.open(filename).read ).inner_text.gsub( "\r", "" )

  output = []

  output << '<tr>'
  output << '<td rowspan=4>' 
  output << pick_name(text)
  output << '</td>' 
  output << '</tr>'

  pick_encount_list(text).each do |entry|
    output << '<tr>'
    output << '<td>'
    output << entry
    output << '</td>' 
    output << '</tr>'
  end
  output << "\n"

  output
=end
  # こっちで作っていたぽい
  in_target = false
  list = []
  File.open(filename).readlines.each do |line|
    line.chomp!
    if line.match '全国 No.' then
      tmp = line.split(' ')
      list << "#{tmp[2]} #{tmp[3]}"
    end

    in_target = true if line.match '<h2.*出現場所'

    if in_target then
      if line.match '<h2.*育成' then
        in_target = false 
      else
        list << line #if target? line
      end
    end
  end
  list << "\n"
  list
end

def download output, dir
  #for i in 1..493
  for i in 100..493
    sleep 1 # リクエスト投げ好きかも？なので
    filename = sprintf("%03d", i) + '.shtml'
    downloaded_file = dir + '/' + filename
    system 'wget ' + 'http://pente.koro-pokemon.com/zukan/' + filename
    system 'mv ' + filename + ' ' + downloaded_file
    parse(downloaded_file).each{ |line| output << "#{line}\n" }
  end
end

def local_parse output
  # output << HEADER
  Dir.entries('./html').each{ |entry|
    if File.extname(entry).match('html') then
      #parse(File.expand_path('html/' + entry)).each{ |line| output << line + "\n" }
      parse(File.expand_path('html/' + entry)).each do |element|
        output << element
      end
    end
  }
  # output << FOOTER
end

#dir = './html'
#system "mkdir -p #{dir}"

output = File.open( './index.html', 'w' )

#download output, dir
local_parse output


