# coding: utf-8

STORE_DIR = './html/'

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
      tmp[2].gsub!(/（.*/, '')
      list << "#{tmp[2]} #{tmp[tmp.length - 1]}"
    end

    in_target = true if line.match '<h2.*出現場所'

    next if not in_target

    if line.match '<h2.*育成' then
      in_target = false 
    else
      list << line #if target? line
    end
  end
  list << "\n"
  list
end

def local_parse output
  Dir.entries(STORE_DIR).each do |entry|
    next if not File.extname(entry).match 'html'
    parse(File.expand_path(STORE_DIR + entry)).each do |element|
      output << element
    end
  end
end

output = File.open( './index.html', 'w' )
local_parse output
output.close
