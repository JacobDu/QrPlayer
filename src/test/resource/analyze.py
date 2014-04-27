# -*- coding:utf-8 -*-

def read_lines(filename):
    with open(filename, mode="r") as f:
        for l in f:
            rec = {}
            for k,v in filter_get_field(l):
                rec[k] = v
            yield rec

def filter_get_field(line):
    for field in line.split('\t'):
        segs = field.split('=', 1)
        if len(segs) != 2:
            continue
        if segs[0] in ['PktId', 'data', 'BaseOpt', 'TnsPktLen']:
            yield segs

def parseFile(filename):
    with open(filename + '.ora', mode='w') as f:
        cnt = 0
        for rec in read_lines(filename):
            if rec.get('BaseOpt','0').strip() == '16':
                line = get_new_line(rec)
                f.write(line + '\n')
                cnt += 1
        # log statistics
        do_simple_report(f)
        # clean up counter
        COUNTER = {}

def do_simple_report(f):
    f.write('simple report \n')
    for k,v in COUNTER.iteritems():
        f.write('F' + str(k) + ':' + str(len(COUNTER[k])) + ' ')

def get_new_line(rec):
    fields = []
    pktid = rec['PktId']
    datalen = rec['TnsPktLen']
    base_opt = rec.get('BaseOpt','0').strip()
    data = rec.get('data','None')
    segs = split_data(data)

    fields.append(segs)
    fields.append(pktid)
    fields.append(datalen)
    fields.append(base_opt)

    return '\t'.join(fields)
    

def split_data(data):
    segs = []
    cur = [0, ]
    cnt = [0, ]
    
    def get_seg( length=0):
        cnt[0] += 1
        limit = len(data)
        start = cur[0]
        result = ''
        if length <= 0 | (start + length > limit):
            cur[0] = limit
            result = data[start:]
        else:
            cur[0] += length
            result = data[start:start + length]
        make_statistics(cnt[0], result)
        return result
          

    segs.append(get_seg(2)) #base option
    segs.append(get_seg(2)) #fixed
    segs.append(get_seg(32)) #scrambled
    segs.append(get_seg(2))  # 78
    segs.append(get_seg(-1)) #remaing
    
    return ' '.join(segs)

def make_statistics(field_id, value):
    if field_id not in COUNTER:
        COUNTER[field_id] = {value:0}
        return
    if value not in COUNTER[field_id]:
        COUNTER[field_id][value] = 0
        return
    COUNTER[field_id][value] += 1
    

def main():
    parseFile('db.pcap.btr')
    parseFile('ORACLE_TNS.pcap.btr')

COUNTER = {}

if __name__ == '__main__':
    main()
