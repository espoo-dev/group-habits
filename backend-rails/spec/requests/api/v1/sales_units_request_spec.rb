require 'rails_helper'

describe 'api/v1/sales_units', type: :request do
  describe '#index' do
    let(:user) { create(:user) }
    let!(:sales_units) { create_list(:sales_unit, 2) }

    before { get api_v1_sales_units_path, headers: auth_headers, as: :json }
    it 'returns status 200 ok' do
      expect(response).to be_successful
    end

    it "returns user's sales_units" do
      expect(json_response.pluck('id')).to match_array(sales_units.pluck(:id))
    end
  end
end
