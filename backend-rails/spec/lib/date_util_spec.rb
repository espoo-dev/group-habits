RSpec.describe DateUtil do
  describe '.parse_date' do
    subject { described_class.parse_date(date) }

    context 'when date is nil' do
      let(:date) { nil }

      it 'returns nil' do
        is_expected.to be_nil
      end
    end

    context 'when date is valid' do
      let(:date) { '25/1/2022' }

      it 'returns formatted date' do
        is_expected.to eq(DateTime.new(2022, 1, 25))
      end
    end

    context 'when date is not valid' do
      let(:date) { '25/111/2022' }

      it 'returns formatted date' do
        expect { subject }.to raise_error Date::Error
      end
    end
  end
end
