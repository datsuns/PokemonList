# coding: utf-8

STORE_DIR = './html/'
BASE_URL = 'http://pente.koro-pokemon.com/zukan/'

def downloaded? file
  FileTest.exists? STORE_DIR + file
end

def run_system_cmd cmd
  puts ">> #{cmd}"
  system cmd
end

def do_download name, output = name
  run_system_cmd 'wget ' + BASE_URL + name
  run_system_cmd 'mv ' + name + ' ' + STORE_DIR + output
end

def numbered_download range
  for i in range
    filename = sprintf("%03d", i) + '.shtml'
    next if downloaded? filename
    do_download filename
  end
end

def parse_index_numer line
  params = line.split ' '
  num = params[0].gsub '<li>', ' '
  num.to_i
end

def parse_index_filename line
  params = line.split ' '
  params[2].split('"')[1]
end

def parse_index filename, range
  list = Hash.new
  File.open(filename).readlines.each do |line|
    next if not line.match /^<li>\d/

    num = parse_index_numer line
    next if not range.include? num

    filename = parse_index_filename line
    list[num] = filename
  end
  list
end

def named_download index_filename
  list = parse_index index_filename, (494..649)
  list.each do |key, value|
    output_name = sprintf("%03d", key) + '.shtml'
    do_download value, output_name
  end
end

def download
  numbered_download (1..493)
  named_download 'index.html'
end

def main
  download
end


main

