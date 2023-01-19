require 'rails_helper'

describe 'api/v1/categories', type: :request do
  describe '#index' do
    let(:user) { create(:user, categories: user_categories) }
    let!(:user_categories) { create_list(:category, 1) }
    let!(:categories2) { create_list(:category, 1) }

    before { get api_v1_categories_path, headers: auth_headers, as: :json }

    it 'returns status 200 ok' do
      expect(response).to be_successful
    end

    it "returns user's categories" do
      expect(json_response.pluck('id')).to match_array(user_categories.pluck(:id))
    end
  end

  describe '#create' do
    let(:user) { create(:user) }

    before do
      post api_v1_categories_path, params: create_category_params, headers: auth_headers, as: :json
    end
    context 'when data is valid' do
      let(:create_category_params) { attributes_for(:category, user_id: nil) }

      it 'returns status 201 created' do
        expect(response).to be_created
      end

      it 'returns category' do
        expect(json_response['id']).to_not be_nil
        expect(json_response['name']).to eq(create_category_params[:name])
      end
    end

    context 'when data is not valid' do
      let(:create_category_params) { attributes_for(:category, name: nil, user_id: nil) }

      it 'returns status 400 bad_request' do
        expect(response).to be_bad_request
      end

      it 'returns category' do
        expect(json_response['error']).to eq("Validation failed: Name can't be blank")
      end
    end
  end

  describe '#destroy' do
    let(:user) { create(:user) }
    let!(:category) { create(:category, user:) }

    before { delete api_v1_category_path(category_id), headers: auth_headers, as: :json }

    describe 'when finds category' do
      let(:category_id) { category.id }
      it 'returns status 204 no_content' do
        expect(response).to be_no_content
      end

      it 'returns an empty hash' do
        expect(response.body).to eq('')
      end
    end

    describe 'when does not find category' do
      let(:category_id) { -1 }

      it 'returns status 404 not_found' do
        expect(response).to be_not_found
      end

      it 'returns proper error message' do
        expect(json_response['error']).to eq("Couldn't find Category with 'id'=-1")
      end
    end
  end

  require 'rails_helper'

describe 'api/v1/categories', type: :request do
  describe '#index' do
    let(:user) { create(:user, categories: user_categories) }
    let!(:user_categories) { create_list(:category, 1) }
    let!(:categories2) { create_list(:category, 1) }

    before { get api_v1_categories_path, headers: auth_headers, as: :json }

    it 'returns status 200 ok' do
      expect(response).to be_successful
    end

    it "returns user's categories" do
      expect(json_response.pluck('id')).to match_array(user_categories.pluck(:id))
    end
  end

  describe '#create' do
    let(:user) { create(:user) }
    let(:create_category_params) { attributes_for(:category, user_id: nil) }

    before do
      post api_v1_categories_path, params: create_category_params, headers: auth_headers, as: :json
    end

    it 'returns status 201 created' do
      expect(response).to be_created
    end

    it 'returns category' do
      expect(json_response['id']).to_not be_nil
      expect(json_response['name']).to eq(create_category_params[:name])
    end
  end

  describe '#destroy' do
    let(:user) { create(:user) }
    let!(:category) { create(:category, user:) }

    before { delete api_v1_category_path(category.id), headers: auth_headers, as: :json }

    it 'returns status 204 no_content' do
      expect(response).to be_no_content
    end

    it 'returns an empty hash' do
      expect(response.body).to eq('')
    end
  end

  describe '#update' do
    let(:user) { create(:user) }
    let!(:category) { create(:category, user:) }

    before { put api_v1_category_path(category.id), headers: auth_headers, as: :json }

    it 'returns status 200 ok' do
      expect(response).to be_successful
    end

    it 'returns status 422' do
      expect(response).to be_unprocessable
    end
  end
end
